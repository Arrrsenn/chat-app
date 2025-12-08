package com.chat.chat_api.service;

import com.chat.chat_api.dto.SendMessageRequest;

public interface MessageSender {

    void sendMessage(SendMessageRequest sendMessageRequest);

}
