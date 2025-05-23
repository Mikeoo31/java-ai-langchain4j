package com.ithui.ai.langchain4j.config;

import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SeparateChatConfig {

    @Bean
    public ChatMemoryProvider chatMemoryProvider() {

        // return memoryId -> MessageWindowChatMemory.builder().id(memoryId).maxMessages(10).build();

        return new ChatMemoryProvider() {
            @Override
            public ChatMemory get(Object memoryId) {
                return MessageWindowChatMemory.builder()
                        .id(memoryId)
                        .maxMessages(10)
                        .build();
            }
        };
    }
}
