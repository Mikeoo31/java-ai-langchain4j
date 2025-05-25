package com.ithui.ai.langchain4j.config;

import com.ithui.ai.langchain4j.store.MongoChatMemoryStore;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SeparateChatConfig {

    //配置持久化的聊天记录
    @Autowired
    private MongoChatMemoryStore mongoChatMemoryStore;

    @Bean
    public ChatMemoryProvider chatMemoryProvider() {

        // return memoryId -> MessageWindowChatMemory.builder().id(memoryId).maxMessages(10).build();

        return new ChatMemoryProvider() {
            @Override
            public ChatMemory get(Object memoryId) {
                return MessageWindowChatMemory.builder()
                        .id(memoryId)
                        .maxMessages(10)
                        // 这里替换成自己的持久化实现
                        .chatMemoryStore(mongoChatMemoryStore)
                        .build();
            }
        };
    }
}
