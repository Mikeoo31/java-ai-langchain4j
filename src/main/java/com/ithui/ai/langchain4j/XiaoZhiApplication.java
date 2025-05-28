package com.ithui.ai.langchain4j;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ithui.ai.langchain4j.mapper")
public class XiaoZhiApplication {

    public static void main(String[] args) {
        SpringApplication.run(XiaoZhiApplication.class, args);
    }
}
