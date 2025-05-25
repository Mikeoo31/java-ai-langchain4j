package com.ithui.ai.langchain4j.config;

import com.ithui.ai.langchain4j.store.MongoChatMemoryStore;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * 创建时间：2025年05月25日
 * <p>
 *
 * <p>
 * 修改时间：2025年05月25日
 * <p>
 *
 * @author wujihui
 * @version v1.0.0
 */
@Configuration
public class XiaoZhiChatConfig {

    // 注入mongodb的chatMemoryProvider
    @Autowired
    private MongoChatMemoryStore mongoChatMemoryStore;

    @Bean
    public ChatMemoryProvider xiaozhiChatMemoryProvider() {
        return memoryId -> MessageWindowChatMemory
                .builder()
                .id(memoryId)
                .maxMessages(20)
                .chatMemoryStore(mongoChatMemoryStore)
                .build();
    }
}
