package com.fanguy.ai.entity.vo;

import lombok.Data;
import org.springframework.ai.chat.messages.Message;

@Data
public class MessageVO {
    private String role;
    private String content;

    public MessageVO(Message message) {
        this.role = switch (message.getMessageType()) {
            case USER -> "user";
            case ASSISTANT -> "assistant";
            case SYSTEM -> "system";
            default -> " ";
        };
        this.content = message.getText();
    }
}
