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

| Module 模块 | Description 说明 | Model 模型 |
|-------------|-------------------|-------------|
| 🤖 **AI Chat** 智能聊天 | Multimodal conversation (text / image / audio) | Alibaba Qwen3.5-omni-plus |
| 📞 **Customer Service** 智能客服 | Function calling with course reservation tools | Alibaba Qwen-plus |
| 💕 **Comfort Simulator** 哄哄模拟器 | Role-playing game "Coax Your Girlfriend" | DeepSeek deepseek-v4-pro |
| 📄 **ChatPDF** 文档问答 | Retrieval-Augmented Generation (RAG) over PDFs | DeepSeek + text-embedding-v4 |

### Key Highlights 技术亮点

- **Multi-Model Architecture** — DeepSeek for games/RAG, Alibaba Bailian for chat/tools
- **Function Calling** — AI autonomously queries courses and creates reservations via Spring AI `@Tool`
- **RAG Pipeline** — PDF ingestion → Vector Store (in-memory) → QuestionAnswerAdvisor
- **Streaming Responses** — All chat endpoints use `Flux<String>` for real-time output
- **OpenAI-Compatible** — Uses Spring AI's OpenAI client pattern to connect to Alibaba DashScope

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
