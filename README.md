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

### AiServices

- AiService是langchain4j的核心接口，它提供了一系列的API，可以让你轻松地与大型语言模型进行交互。
- 核心原理：组装Assistant接口以及其他组件，通过反射机制创建出Assistant的代理对象，通过代理对象封装一些基础逻辑功能。

#### AIService可处理最常见的操作：
- 为大语言模型格式化输入内容
- 解析大语言模型的输出结果
- 其实大模型的在输入和输出方面，并不是简单的接受String，而是需要转换为UserMassage对象（输入）和AiResponse对象（输出）。

#### 它们还支持更高级的功能：
- 聊天记忆 Chat memory
- 工具 Tools
- 检索增强生成 RAG


### ChatMemory

- ChatMemory是langchain4j的聊天记忆功能，它可以让你在聊天机器人中存储和管理对话历史记录。

#### ChatMemory支持以下功能：
- 存储对话历史记录
- 管理对话历史记录
- 实现聊天机器人的上下文管理

#### 持久化聊天记录（Persistence）
- 上述持久化功能的聊天记录默认是基于内存级别的，但也可以将聊天记录持久化到文件系统、数据库或其他存储介质中。这样，你可以在程序重启后，仍然可以获取之前的聊天记录。
- 存储介质的选择：
  - 由于是存储聊天记录文档，因此如果使用数据库存储，建议选择支持JSON格式的数据库且是NoSQL数据库，如MongoDB和Cassandra等。
  - Cassandra是一种分布式NoSQL数据库，适合存储海量的聊天数据。
  - MongoDB是另一种NoSQL数据库，且是一种文档型数据库，数据以JSON-like格式存储，并且具有较高的灵活性和可扩展性。由于用户的聊天记录多数是多变的，如图片、视频、音频等，因此建议使用MongoDB。