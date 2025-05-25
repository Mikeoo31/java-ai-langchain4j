package com.ithui.ai.langchain4j.assistant;

import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;

import static dev.langchain4j.service.spring.AiServiceWiringMode.EXPLICIT;

@AiService(
        wiringMode = EXPLICIT,
        chatModel = "qwenChatModel",
        chatMemory = "chatMemory"
)
public interface ChatMemoryAssistant {

    @UserMessage("你是我的好朋友，请用上海话回答问题，并且添加一些表情符号。 {{it}}") // it是表示用户输入的占位符，即input
    String chat(String input);
}
