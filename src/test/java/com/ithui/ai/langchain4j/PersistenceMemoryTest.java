package com.ithui.ai.langchain4j;

import com.ithui.ai.langchain4j.assistant.SeparateChatAssistant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PersistenceMemoryTest {

    @Autowired
    private SeparateChatAssistant separateChatAssistant;

    @Test
    public void testPersistenceMemory() {

        String answer1 = separateChatAssistant.chat(1,"你好,我是你爹");
        System.out.println(answer1);

        String answer2 = separateChatAssistant.chat(1,"你好,我是谁?");
        System.out.println(answer2);

        String answer3 = separateChatAssistant.chat(2,"你好,我是谁?");
        System.out.println(answer3);
    }
}
