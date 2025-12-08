package com.chat.chat_api.service;

import com.chat.chat_api.model.ChatMessage;

public interface MessageReceiver {

    void getChatMessage(ChatMessage chatMessage);

}
