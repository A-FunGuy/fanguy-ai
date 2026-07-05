package com.fanguy.ai.controller;

import com.fanguy.ai.service.ISpringAiChatRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@RestController
@RequestMapping("/ai")
public class CustomerServiceController {

    private final ChatClient serviceClient;
    private final ISpringAiChatRecordService recordService;

    // 请求方式和路径不要改动，将来要与前端联调
    @RequestMapping(value = "/service", produces = "text/html;charset=UTF-8")
    public Flux<String> service(@RequestParam(defaultValue = "讲个笑话") String prompt,
                             @RequestParam("chatId") String chatId) {
        //保存会话记录
        recordService.saveRecord("service", chatId);

        return serviceClient
                .prompt(prompt)
                .advisors(as -> as.param(ChatMemory.CONVERSATION_ID, chatId))
                .stream() // 流式调用
                .content();
    }
}