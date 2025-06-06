package com.ithui.ai.langchain4j.controller;

import com.ithui.ai.langchain4j.assistant.XiaoZhiChatAssistant;
import com.ithui.ai.langchain4j.bean.ChatForm;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;


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
@RestController
@RequestMapping("/xiaozhi")
public class XiaoZhiController {

    @Autowired
    private XiaoZhiChatAssistant xiaoZhiChatAssistant;

    @PostMapping(value = "/chat",produces = "text/stream;charset=utf-8")
    @Operation(summary = "聊天接口", description = "聊天接口")
    public Flux<String> chat(@RequestBody ChatForm chatForm){
        return xiaoZhiChatAssistant.chat(chatForm.getMemoryId(), chatForm.getMessage());
    }
}
