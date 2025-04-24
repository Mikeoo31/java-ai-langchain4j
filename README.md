## Java的大模型开发框架 -- langchain4j

### langchain4j: 简化将大预言模型集成到Java的应用开发过程中

#### 功能

- 与大语言模型和向量数据库的便捷交互

  通过统一的应用程序编程接口（API），可以轻松访问所有主要的商业和开源大型语言模型以及向量数据库，使你能够构建聊天机器人、智能助手等应用

- 专为Java打造

  借助Spring Boot 集成，能够将大模型集成到ava 应用程序中。大型语言模型与 Java 之间实现了双向集成：你可以从 Java 中调用大型语言模型，同时也允许大型语言模型反过来调用你的 Java 代码

- 智能代理、工具、检索增强（RAG）

  为常见的大语言模型操作提供了广泛的工具，涵盖从底层的提示词模板创建、聊天记忆管理和输出解析，到智能代理和检索增强生成等高级模式

#### 使用

为了可以使用spring提供的自动配置类，在导入坐标时，可以使用其相关的starter，例如：

```java
    // open-ai整合的starter
    <dependency>
        <groupId>dev.langchain4j</groupId>
        <artifactId>langchain4j-open-ai-spring-boot-starter</artifactId>
    </dependency>
        
    // 接入ollama
    <dependency>
        <groupId>dev.langchain4j</groupId>
        <artifactId>langchain4j-ollama-spring-boot-starter</artifactId>
    </dependency>

    // 接入阿里云百炼平台
    <dependency>
        <groupId>dev.langchain4j</groupId>
        <artifactId>langchain4j-community-dashscope-spring-boot-starter</artifactId>
    </dependency>
```