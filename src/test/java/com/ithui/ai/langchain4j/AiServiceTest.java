package com.ithui.ai.langchain4j;

import com.ithui.ai.langchain4j.assistant.Assistant;
import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.service.AiServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AiServiceTest {

    /**
     * 普通方式注入
     */
    @Autowired
    private QwenChatModel qwenChatModel;
    @Test
    public void testAiService() {
        Assistant assistant = AiServices.create(Assistant.class, qwenChatModel);
        String answer = assistant.chat("你好");
        System.out.println(answer);
    }

    /**
     * 注解方式注入
     */
    @Autowired
    private Assistant assistant;
    @Test
    public void testAiService2() {
        String answer = assistant.chat("你是谁？");
        System.out.println(answer);
    }
}
