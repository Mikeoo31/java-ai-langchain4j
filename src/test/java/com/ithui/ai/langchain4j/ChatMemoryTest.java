package com.ithui.ai.langchain4j;

import com.ithui.ai.langchain4j.assistant.Assistant;
import com.ithui.ai.langchain4j.assistant.ChatMemoryAssistant;
import com.ithui.ai.langchain4j.assistant.SeparateChatAssistant;
import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.service.AiServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ChatMemoryTest {

    /**
     * 简单的记忆聊天功能测试
     */
    @Autowired
    private QwenChatModel qwenChatModel;

    @Test
    public void testChatMemory1() {

        UserMessage userMessage1 = new UserMessage("你好,我是你爹");
        ChatResponse chatResponse = qwenChatModel.chat(userMessage1);
        AiMessage aiMessage1 = chatResponse.aiMessage();
        System.out.println(aiMessage1.text());

        UserMessage userMessage2 = new UserMessage("你好,我是谁?");
        ChatResponse chatResponse2 = qwenChatModel.chat(userMessage1,aiMessage1, userMessage2);
        AiMessage aiMessage2 = chatResponse2.aiMessage();
        System.out.println(aiMessage2.text());
    }

    /**
     * 使用AiService的记忆聊天功能测试
     */
    @Test
    public void testChatMemory2() {

        // 使用AiService的记忆聊天功能，记忆窗口大小为10
        ChatMemory chatMemory = MessageWindowChatMemory.builder().maxMessages(10).build();

        Assistant assistant = AiServices
                .builder(Assistant.class)
                .chatLanguageModel(qwenChatModel)
                // 使用AiService的记忆聊天功能
                .chatMemory(chatMemory)
                .build();

        String answer1 = assistant.chat("你好,我是你爹");
        System.out.println(answer1);

        String answer2 = assistant.chat("你好,我是谁?");
        System.out.println(answer2);
    }

    /**
     * 使用AiService的记忆聊天功能测试（直接使用注解）
     */
    @Autowired
    private ChatMemoryAssistant assistant;

    @Test
    public void testChatMemory3() {

        String answer1 = assistant.chat("你好,我是你爹");
        System.out.println(answer1);

        String answer2 = assistant.chat("你好,我是谁?");
        System.out.println(answer2);
    }

    /**
     * 使用AiService的记忆聊天功能和会话隔离测试
     */
    @Autowired
    private SeparateChatAssistant separateChatAssistant;

    @Test
    public void testChatMemory4() {

        String answer1 = separateChatAssistant.chat(1,"你好,我是你爹");
        System.out.println(answer1);

        String answer2 = separateChatAssistant.chat(1,"你好,我是谁?");
        System.out.println(answer2);

        String answer3 = separateChatAssistant.chat(2,"你好,我是谁?");
        System.out.println(answer3);
    }
}
