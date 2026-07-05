# 方盖 AI · FanGuy AI

<div align="center">

AI 应用平台：多模态聊天、智能客服、RAG 问答、角色扮演游戏

**Spring AI 实战项目 · 多模型 LLM 应用平台**

[![Java](https://img.shields.io/badge/Java-17-orange)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen)](https://spring.io/projects/spring-boot)
[![Spring AI](https://img.shields.io/badge/Spring%20AI-1.1.8-blue)](https://spring.io/projects/spring-ai)
[![Vue.js](https://img.shields.io/badge/Vue.js-3.x-4FC08D)](https://vuejs.org/)
[![MySQL](https://img.shields.io/badge/MySQL-8.x-4479A1)](https://www.mysql.com/)
[![License](https://img.shields.io/badge/license-MIT-green)](LICENSE)

A practical AI application platform built with Spring AI, integrating DeepSeek & Alibaba DashScope (OpenAI-compatible API), featuring multimodal chat, function calling, RAG, and role-playing game.

[Features](#features) · [Preview](#preview) · [Quick Start](#quick-start) · [Architecture](#architecture)

</div>

---

## Features 功能

### 🤖 AI Chat 智能聊天

基于 **阿里云百炼 Qwen3.5-omni-plus**，支持流式多模态对话：

- **文字聊天** — 自然对话，带会话记忆（最近 20 条消息），支持连续上下文
- **图片识别** — 上传图片进行视觉识别和问答，适合各类图文场景
- **音频处理** — 上传音频文件，AI 自动转录并分析内容
- **流式输出** — 基于 `Flux<String>` 实现 token 级别实时输出，无需等待完整响应
- **记忆持久化** — 会话历史通过 `JdbcChatMemoryRepository` 存入 MySQL，重启不丢失

> *Multimodal streaming chat with text / image / audio powered by Alibaba Qwen3.5-omni-plus.*

### 📞 Customer Service 智能客服

基于 **阿里云百炼 Qwen-plus**，结合 Spring AI **函数调用**（`@Tool`）：

- **课程查询** — AI 读取课程数据库，回答用户关于课程类型、价格、学历要求等问题
- **校区查询** — 自动查询校区列表，为用户推荐最近的上课地点
- **自动预约** — AI 收集学生姓名、电话、课程、校区等信息，自动创建预约记录
- **无缝追问** — 缺少必填字段时，AI 会自然地向用户追问，补全信息后再调用工具
- **真实业务闭环** — 从咨询到预约全流程自动化，可直接用于线上客服场景

> *Intelligent customer service with function calling — AI queries courses and creates reservations autonomously via Spring AI @Tool.*

### 💕 Comfort Simulator 哄哄模拟器

基于 **DeepSeek deepseek-v4-pro**，"哄女友大作战"角色扮演小游戏：

- **原谅值系统** — 0/100 分数机制，每轮对话根据质量 ± 分数，满分通关
- **情绪变化** — AI 扮演生气 / 冷漠 / 缓和 / 开心等多种情绪状态，语气生动传神
- **三种难度** — 普通 / 困难 / 地狱模式，影响女友脾气和原谅难度
- **回合限制** — 40 回合或达成 100 原谅值游戏结束，附带胜利/失败结局
- **实时反馈** — 每句对话实时显示情绪状态 + 得分变化 + 原谅值进度

> *Role-playing game with forgiveness meter, emotional states, and 3 difficulty levels powered by DeepSeek.*

### 📄 ChatPDF 文档问答

基于 **DeepSeek deepseek-v4-pro** + **text-embedding-v4 (1024维)**，完整的 RAG 检索增强生成链路：

- **PDF 上传** — 拖拽上传 PDF 文件，服务端校验 `application/pdf` 格式
- **向量索引** — PDF 解析后通过 `SimpleVectorStore` 建立向量索引，配合 `QuestionAnswerAdvisor` 检索
- **语义检索** — 每次查询取 `topK=2` 个最相关文档片段，相似度阈值 0.5
- **上下文隔离** — 通过 `chatId` 过滤表达式，不同会话的文档互不干扰
- **文件回传** — 已上传的 PDF 可通过 `/ai/pdf/file/{chatId}` 接口下载

> *RAG pipeline — PDF upload → vector indexing → semantic search → context-aware Q&A powered by DeepSeek + text-embedding-v4.*

### Key Highlights

- **Multi-Model Architecture** — DeepSeek for games/RAG, Alibaba Bailian for chat/tools
- **OpenAI-Compatible** — Uses Spring AI's OpenAI client pattern to connect to Alibaba DashScope
- **Streaming All The Way** — Every chat endpoint returns `Flux<String>` for real-time output

## Preview 功能预览

### 🤖 AI Chat — Multimodal conversation

![AI Chat](docs/images/ai-chat.png)

### 📞 Customer Service — Tool calling

![Customer Service](docs/images/customer-service.png)

### 💕 Comfort Simulator — Role-playing game

![Comfort Simulator](docs/images/comfort-simulator.png)

### 📄 ChatPDF — RAG document Q&A

![ChatPDF](docs/images/chat-pdf.png)

## Tech Stack 技术栈

| Layer 层级 | Technology |
|------------|------------|
| **Framework** | Spring Boot 3.x + Spring AI 1.1.8 |
| **LLM Providers** | DeepSeek API · Alibaba DashScope (Bailian) |
| **Embedding** | text-embedding-v4 (1024-dim) |
| **Vector Store** | SimpleVectorStore (in-memory) |
| **ORM** | MyBatis-Plus |
| **Database** | MySQL 8.x |
| **Chat Memory** | JdbcChatMemoryRepository |
| **Frontend** | Vue.js 3 + Vite |
| **Reverse Proxy** | nginx |

## Quick Start 快速开始

### Prerequisites

- JDK 17+
- MySQL 8.x
- Maven 3.8+

### Environment Variables 环境变量

```bash
DEEPSEEK_API_KEY=sk-xxx         # DeepSeek API key
QWEN_API_KEY=sk-xxx             # Alibaba Bailian API key
MYSQL_PASSWORD=xxx              # MySQL password
```

### Database 数据库初始化

```bash
# 1. Create database + chat memory table
mysql -u root -p < fanguy-ai/src/main/resources/sql/schema-mysql.sql

# 2. Course / school / reservation tables + seed data
mysql -u root -p < fanguy-ai/src/main/resources/sql/CustomerService.sql
```

### Run 启动

```bash
# Backend
cd fanguy-ai
mvn spring-boot:run

# Frontend (nginx)
cd spring-ai-nginx
nginx.exe
```

Visit `http://localhost` in your browser.

## Architecture 项目结构

```
fanguy-ai/
├── fanguy-ai/                    # Spring Boot Backend
│   ├── src/main/java/com/fanguy/ai/
│   │   ├── config/               # Bean configuration (Spring AI clients)
│   │   ├── controller/           # REST API endpoints
│   │   ├── constants/            # System prompts
│   │   ├── entity/               # PO / Query / VO
│   │   ├── mapper/               # MyBatis Mapper interfaces
│   │   ├── service/              # Business logic
│   │   ├── tools/                # AI @Tool definitions (function calling)
│   │   └── utils/                # Utilities
│   └── src/main/resources/
│       ├── application.yaml      # Spring AI & datasource config
│       ├── mapper/               # MyBatis XML
│       └── sql/                  # DDL & seed data
└── spring-ai-nginx/              # nginx + Vue.js Frontend
    ├── conf/                     # nginx configuration
    └── html/                     # Static SPA assets
```

## Keywords

`spring-ai` `deepseek` `openai-compatible` `rag` `function-calling` `chatbot` `llm` `embedding` `vector-store` `vue` `ai-platform` `multimodal`

## License

MIT
