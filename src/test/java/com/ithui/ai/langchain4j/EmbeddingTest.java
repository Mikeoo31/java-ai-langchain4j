package com.ithui.ai.langchain4j;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

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
@SpringBootTest
public class EmbeddingTest {
    @Autowired
    private EmbeddingModel embeddingModel;
    @Test
    public void testEmbeddingModel(){
        Response<Embedding> embed = embeddingModel.embed("你好");

        System.out.println("向量维度：" + embed.content().vector().length);
        System.out.println("向量输出：" + embed.toString());
    }



    @Autowired
    private EmbeddingStore embeddingStore;
    @Test
    public void testPineconeEmbeded() {
        //将文本转换成向量
        TextSegment segment1 = TextSegment.from("我喜欢羽毛球");
        Embedding embedding1 = embeddingModel.embed(segment1).content();
        //存入向量数据库
        embeddingStore.add(embedding1, segment1);
        TextSegment segment2 = TextSegment.from("今天天气很好");
        Embedding embedding2 = embeddingModel.embed(segment2).content();
        embeddingStore.add(embedding2, segment2);
    }

    @Test
    public void testUploadKnowledgeLibrary() {
        // 1.读取知识库
        Document hospitalInfo = FileSystemDocumentLoader.loadDocument("E:/knowledge/医院信息.md");
        Document departmentInfo = FileSystemDocumentLoader.loadDocument("E:/knowledge/科室信息.md");
        Document neurologyInfo = FileSystemDocumentLoader.loadDocument("E:/knowledge/神经内科.md");
        List<Document> documentList = Arrays.asList(hospitalInfo, departmentInfo, neurologyInfo);

        //文本向量化并存入向量数据库：将每个片段进行向量化，得到一个嵌入向量
        EmbeddingStoreIngestor
                .builder()
                .embeddingStore(embeddingStore)
                .embeddingModel(embeddingModel)
                .build()
                .ingest(documentList);

    }
}
