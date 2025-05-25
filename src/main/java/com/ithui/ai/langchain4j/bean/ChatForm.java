package com.ithui.ai.langchain4j.bean;

import lombok.Data;

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
@Data
public class ChatForm {
    // 对话id
    private Long memoryId;

    // 用户问题
    private String message;

}
