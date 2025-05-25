package com.ithui.ai.langchain4j.assistant;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import dev.langchain4j.service.spring.AiService;

import static dev.langchain4j.service.spring.AiServiceWiringMode.EXPLICIT;

/**
 * 隔离式聊天助手接口
 */
@AiService(
        wiringMode = EXPLICIT,
        chatModel = "qwenChatModel",
        chatMemoryProvider = "chatMemoryProvider"
)
public interface SeparateChatAssistant {

    /**
     * 由于需要隔离式的聊天助手，所以需要提供一个聊天ID，用于标识当前的会话。
     * @param memoryId
     * @param userMessage
     * @return
     */
    // @SystemMessage("我是你的好朋友，请用东北话回答我的问题。今天是{{current_date}}") //系统提示词
    @SystemMessage(fromResource = "my-prompt-template.txt") // 系统提示词，从资源文件中加载
    String chat(@MemoryId int memoryId, @UserMessage String userMessage);

    // @V注解用于注入变量，这里的username和age是自定义的变量名,自定义传入变量的名称，否则就只能是it
    @SystemMessage(fromResource = "my-prompt-template1.txt")
    String chat2(@MemoryId int memoryId,
                 @UserMessage String userMessage,
                 @V("username") String username,
                 @V("age") int age);
}
