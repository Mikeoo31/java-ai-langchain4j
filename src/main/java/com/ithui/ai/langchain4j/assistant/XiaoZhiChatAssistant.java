package com.ithui.ai.langchain4j.assistant;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;
import reactor.core.publisher.Flux;

import static dev.langchain4j.service.spring.AiServiceWiringMode.EXPLICIT;

/**
 * @author wujihui
 */
@AiService(
        wiringMode = EXPLICIT,
        /*chatModel = "qwenChatModel",*/
        streamingChatModel = "qwenStreamingChatModel",
        chatMemoryProvider = "xiaozhiChatMemoryProvider",
        tools = "appointmentTool" ,//tools配置
        contentRetriever = "contentRetrieverXiaozhiPincone" //contentRetriever配置
)
public interface XiaoZhiChatAssistant {

    @SystemMessage(fromResource = "xiaozhi-prompt-template.txt")
    Flux<String> chat(@MemoryId Long memoryId, @UserMessage String userMessage);
}
