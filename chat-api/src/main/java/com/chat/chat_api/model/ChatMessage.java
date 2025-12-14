package com.chat.chat_api.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {
    private String username;
    private String content;
    private String roomId;
    private LocalDateTime timestamp;


    @Override
    public String toString() {
        return "ChatMessage{" +
                "username='" + username + '\'' +
                ", content='" + content + '\'' +
                ", roomId='" + roomId + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
