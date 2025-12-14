package com.chat.chat_consumer_service.service;

import com.chat.chat_api.model.ChatMessage;
import com.chat.chat_api.service.MessageReceiver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Slf4j
public class IncomingMessagesImpl implements MessageReceiver {

    private final SimpMessagingTemplate simpMessagingTemplate;

    public IncomingMessagesImpl(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @Override
    @KafkaListener(
            topics = "${spring.kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void getChatMessage(ChatMessage chatMessage) {
        log.info("Message receiver => {}", chatMessage);
        String destination = String.format(
                "/%s/%s/%s",
                "topic",
                "chat",
                chatMessage.getRoomId());
        log.info("destination: {}", destination);
        simpMessagingTemplate.convertAndSend(destination, chatMessage);
    }
}
