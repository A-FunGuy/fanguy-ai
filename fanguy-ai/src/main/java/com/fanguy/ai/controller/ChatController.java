package com.fanguy.ai.controller;

import com.fanguy.ai.service.ISpringAiChatRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.content.Media;
import org.springframework.util.MimeType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@RestController
@RequestMapping("/ai")
public class ChatController {

    private final ChatClient chatClient;

    private final ISpringAiChatRecordService recordService;

    // 请求方式和路径不要改动，将来要与前端联调
    @RequestMapping(value = "/chat", produces = "text/html;charset=UTF-8")
    public Flux<String> chat(@RequestParam(defaultValue = "讲个笑话") String prompt,
                             @RequestParam("chatId") String chatId,
                             @RequestParam(value = "files", required = false) List<MultipartFile> files) {
        //保存会话记录
        recordService.saveRecord("chat", chatId);

        if (files == null || files.isEmpty()) {
            //没有附件，纯文本聊天
            return textChat(prompt, chatId);
        }else {
            //有附件，多模态聊天
            return multiModalChat(prompt, chatId, files);
        }

    }

    private Flux<String> textChat(String prompt, String chatId) {
        return chatClient
                .prompt(prompt)
                .advisors(as -> as.param(ChatMemory.CONVERSATION_ID, chatId))
                .stream() // 流式调用
                .content();
    }

    private Flux<String> multiModalChat(String prompt, String chatId, List<MultipartFile> files) {
        //1.解析多媒文件
        List<Media> medias = files.stream().map(file -> new Media(
                MimeType.valueOf(Objects.requireNonNull(file.getContentType())),
                file.getResource())
        ).toList();
        //2.请求模型
        return chatClient
                .prompt()
                .user(p -> p.text(prompt).media(medias.toArray(Media[] ::new)))
                .advisors(as -> as.param(ChatMemory.CONVERSATION_ID, chatId))
                .stream() // 流式调用
                .content();
    }
}