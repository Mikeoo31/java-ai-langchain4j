package com.ithui.ai.langchain4j;

import com.ithui.ai.langchain4j.assistant.ChatMemoryAssistant;
import com.ithui.ai.langchain4j.assistant.SeparateChatAssistant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
@SpringBootTest
public class PromptTest {

    @Autowired
    private SeparateChatAssistant assistant;
    @Test
    public void testPrompt1(){
//        String answer = assistant.chat(3,"我是你爹，你是谁？");
//        System.out.println(answer);

        String answer1 = assistant.chat(3,"我是谁，今天几号？");
        System.out.println(answer1);
    }

    @Autowired
    private ChatMemoryAssistant assistant2;
    @Test
    public void testPrompt2(){

        String answer = assistant2.chat("我是谁，今天几号？");
        System.out.println(answer);
    }

    @Test
    public void testPrompt3(){

        // 假设从数据库中获取到用户信息
        String username = "wujihui";
        int age = 25;

        String answer = assistant.chat2(10,"我是谁，今天几号？",username,age);
        System.out.println(answer);
    }
}
