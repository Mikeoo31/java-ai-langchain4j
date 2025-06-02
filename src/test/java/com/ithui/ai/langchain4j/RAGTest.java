package com.ithui.ai.langchain4j;

import com.alibaba.dashscope.tokenizers.QwenTokenizer;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.splitter.DocumentByParagraphSplitter;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.onnx.HuggingFaceTokenizer;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
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
@SpringBootTest
public class RAGTest {

    @Test
    public void testReadDocument(){
        // 使用FileSystemDocumentLoader读取指定目录下的知识库文档
        // 并使用默认的文档解析器TextDocumentParser对文档进行解析
        Document document = FileSystemDocumentLoader.loadDocument("E:/knowledge/测试.txt");
        //System.out.println(document.text());

        // langchain4j自带的基于内存的embedding store
        InMemoryEmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();

        //ingest
        /*
        1、分割文档：默认使用递归分割器，将文档分割为多个文本片段，每个片段包含不超过300个token，并且有30个token的重叠部分保证连贯性
        2、文本向量化：使用一个LangChain4j内置的轻量化向量模型对每个文本片段进行向量化
        3、将原始文本和向量存储到向量数据库中(InMemoryEmbeddingStore)
        */
        EmbeddingStoreIngestor.ingest(document, embeddingStore);

        System.out.println(embeddingStore);
    }

    @Test
    public void testDocumentSplitter(){
        Document document = FileSystemDocumentLoader.loadDocument("E:/knowledge/测试.txt");

        // 定义inmemory store
        InMemoryEmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();

        // 自定义分割器
        DocumentByParagraphSplitter documentByParagraphSplitter = new DocumentByParagraphSplitter(
                300,
                30,
                // token 分词器，按照token计算，不同的分词器可以实现不同的分词策略
                new HuggingFaceTokenizer()
        );

        // 建造者模式
        EmbeddingStoreIngestor.builder()
               .embeddingStore(embeddingStore)
               .documentSplitter(documentByParagraphSplitter)
               .build()
               .ingest(document);
    }
}
