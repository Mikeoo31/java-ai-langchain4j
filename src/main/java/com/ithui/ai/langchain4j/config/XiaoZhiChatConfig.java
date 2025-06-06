package com.ithui.ai.langchain4j.config;

import com.ithui.ai.langchain4j.store.MongoChatMemoryStore;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 创建时间：2025年05月25日
 * <p>
 *
 * <p>
 * 修改时间：2025年05月25日
 * <p>
 *
 * @author wujihui
 * @version v1.0.0
 */
@Configuration
public class XiaoZhiChatConfig {

    // 注入mongodb的chatMemoryProvider
    @Autowired
    private MongoChatMemoryStore mongoChatMemoryStore;
    @Autowired
    private EmbeddingStore embeddingStore;
    @Autowired
    private EmbeddingModel embeddingModel;

    @Bean
    public ChatMemoryProvider xiaozhiChatMemoryProvider() {
        return memoryId -> MessageWindowChatMemory
                .builder()
                .id(memoryId)
                .maxMessages(20)
                .chatMemoryStore(mongoChatMemoryStore)
                .build();
    }

    // 配置RAG内容检索器
    @Bean
    public ContentRetriever xiaozhiContentRetriever() {
        // 1.读取知识库
        Document hospitalInfo = FileSystemDocumentLoader.loadDocument("E:/knowledge/医院信息.md");
        Document departmentInfo = FileSystemDocumentLoader.loadDocument("E:/knowledge/科室信息.md");
        Document neurologyInfo = FileSystemDocumentLoader.loadDocument("E:/knowledge/神经内科.md");
        List<Document> documentList = Arrays.asList(hospitalInfo, departmentInfo, neurologyInfo);

        // 2.初始化向量存储
        InMemoryEmbeddingStore<TextSegment> inMemoryEmbeddingStore = new InMemoryEmbeddingStore<>();

        // 3.使用默认的文档分割器
        EmbeddingStoreIngestor.ingest(documentList,inMemoryEmbeddingStore);

        //从嵌入存储（EmbeddingStore）里检索和查询内容相关的信息
        return EmbeddingStoreContentRetriever.from(inMemoryEmbeddingStore);
    }

    @Bean
    ContentRetriever contentRetrieverXiaozhiPincone() {
        // 创建一个 EmbeddingStoreContentRetriever 对象，用于从嵌入存储中检索内容
        return EmbeddingStoreContentRetriever
                .builder()
                // 设置用于生成嵌入向量的嵌入模型
                .embeddingModel(embeddingModel)
                // 指定要使用的嵌入存储
                .embeddingStore(embeddingStore)
                // 设置最大检索结果数量，这里表示最多返回 1 条匹配结果
                .maxResults(1)
                // 设置最小得分阈值，只有得分大于等于 0.8 的结果才会被返回
                .minScore(0.8)
                // 构建最终的 EmbeddingStoreContentRetriever 实例
                .build();
    }
}
