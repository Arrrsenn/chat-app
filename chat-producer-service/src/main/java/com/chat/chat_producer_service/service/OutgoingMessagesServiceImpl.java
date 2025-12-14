package com.chat.chat_producer_service.service;

import com.chat.chat_api.dto.SendMessageRequest;
import com.chat.chat_api.model.ChatMessage;
import com.chat.chat_api.service.MessageSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.LocalDateTime;

@Slf4j
public class OutgoingMessagesServiceImpl implements MessageSender {


    @Value("${spring.kafka.topic.name}")
    private String topicName;
    private final KafkaTemplate<String, ChatMessage> kafkaTemplate;

    public OutgoingMessagesServiceImpl(KafkaTemplate<String, ChatMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendMessage(SendMessageRequest sendMessageRequest) {
        ChatMessage chatMessage = new ChatMessage(
                sendMessageRequest.getUsername(),
                sendMessageRequest.getContent(),
                sendMessageRequest.getRoomId(),
                LocalDateTime.now());
        kafkaTemplate.send(topicName, chatMessage);
        log.info("Message sent => {} -- {}", chatMessage.getUsername(), chatMessage.getContent());
    }
}
