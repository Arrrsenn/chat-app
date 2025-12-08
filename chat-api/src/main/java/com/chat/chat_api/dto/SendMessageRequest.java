package com.chat.chat_api.dto;


import lombok.Data;

@Data
public class SendMessageRequest {

    private String username;
    private String content;
}
