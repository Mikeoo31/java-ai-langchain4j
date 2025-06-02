package com.ithui.ai.langchain4j.config;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.pinecone.PineconeEmbeddingStore;
import dev.langchain4j.store.embedding.pinecone.PineconeServerlessIndexConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 
 *
 * <p>
 * 创建时间：2025年06月02日
 * <p>
 *
 * <p>
 * 修改时间：2025年06月02日
 * <p>
 *
 * @author wujihui
 * @version v1.0.0
 */
@Configuration
public class EmbeddingStoreConfig {

    @Value("${pinecone.api-key}")
    private String PINECONE_API_KEY;
    @Autowired
    private EmbeddingModel embeddingModel;

    @Bean
    public EmbeddingStore<TextSegment> embeddingStore() {
//创建向量存储
        EmbeddingStore<TextSegment> embeddingStore = PineconeEmbeddingStore.builder()
                .apiKey(PINECONE_API_KEY)
                //如果指定的索引不存在，将创建一个新的索引
                .index("xiaozhi-index")
                //如果指定的名称空间不存在，将创建一个新的名称 空间
                .nameSpace("xiaozhi-namespace")
                .createIndex(PineconeServerlessIndexConfig.builder()
                        //指定索引部署在 AWS 云服务上。
                        .cloud("AWS")
                        //指定索引所在的 AWS 区域为 us-east-1。
                        .region("us-east-1")
                        //指定索引的向量维度，该维度与 embeddedModel 生成的向量维度相同。
                        .dimension(embeddingModel.dimension())
                        .build())
                .build();
        return embeddingStore;
    }
}
