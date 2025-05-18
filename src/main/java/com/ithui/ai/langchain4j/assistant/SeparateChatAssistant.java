package com.ithui.ai.langchain4j.assistant;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.UserMessage;
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
    String chat(@MemoryId int memoryId, @UserMessage String userMessage);
}
