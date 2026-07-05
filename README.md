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

Powered by **Alibaba Qwen3.5-omni-plus**, supports streaming multimodal conversation:

- **Text** — Natural conversation with chat memory (last 20 messages)
- **Image** — Upload images for visual recognition and Q&A
- **Audio** — Upload audio files for transcription and analysis
- **Streaming** — Real-time token-by-token output via `Flux<String>`, no waiting for full response
- **Memory** — Conversation history persisted in MySQL via `JdbcChatMemoryRepository`

### 📞 Customer Service 智能客服

Powered by **Alibaba Qwen-plus** with Spring AI **Function Calling** (`@Tool`)：

- **Course Inquiry** — AI reads the course database to answer "有哪些编程课程？" "Java 多少钱？"
- **Campus Lookup** — AI queries school campuses to recommend the nearest location
- **Auto Reservation** — AI fills in student name, phone, course, and campus to create a reservation record
- **Seamless Handoff** — When AI leaves out a required field, it naturally asks the user before calling the tool

### 💕 Comfort Simulator 哄哄模拟器

Powered by **DeepSeek deepseek-v4-pro**, a "Coax Your Girlfriend" role-playing game:

- **Forgiveness Meter** — 0/100 scoring system with ± point changes based on dialogue quality
- **Emotional States** — AI role-plays hurt / cold / softening / happy girlfriend with expressive dialogue
- **3 Difficulty Modes** — Easy / Normal / Hard determine girlfriend's temper and forgiveness threshold
- **40-message Limit** — Game ends at 40 turns or 100 forgiveness, with win/lose epilogue
- **Streaming Output** — Real-time game narration, no lag between turns

### 📄 ChatPDF 文档问答

Powered by **DeepSeek deepseek-v4-pro** + **text-embedding-v4 (1024-dim)**, a complete RAG pipeline:

- **PDF Upload** — Drag & drop PDF files; validated server-side as `application/pdf`
- **Vector Indexing** — PDF parsed and embedded into `SimpleVectorStore` with `QuestionAnswerAdvisor`
- **Semantic Search** — `topK=2` most relevant chunks retrieved per query with 0.5 similarity threshold
- **Context-Aware** — Each query scoped to the current conversation (`chatId`) via filter expressions
- **File Download** — Previously uploaded PDFs can be downloaded via `/ai/pdf/file/{chatId}`

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
