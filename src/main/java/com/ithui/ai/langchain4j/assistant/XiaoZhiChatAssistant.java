package com.ithui.ai.langchain4j.assistant;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;

import static dev.langchain4j.service.spring.AiServiceWiringMode.EXPLICIT;

/**
 * @author wujihui
 */
@AiService(
        wiringMode = EXPLICIT,
        chatModel = "qwenChatModel",
        chatMemoryProvider = "xiaozhiChatMemoryProvider",
        tools = "appointmentTool" ,//tools配置
        contentRetriever = "xiaozhiContentRetriever" //contentRetriever配置
)
public interface XiaoZhiChatAssistant {

    @SystemMessage(fromResource = "xiaozhi-prompt-template.txt")
    String chat(@MemoryId Long memoryId, @UserMessage String userMessage);
}
