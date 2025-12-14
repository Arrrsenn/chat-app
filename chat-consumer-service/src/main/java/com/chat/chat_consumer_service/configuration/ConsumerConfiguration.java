package com.chat.chat_consumer_service.configuration;

import com.chat.chat_api.service.MessageReceiver;
import com.chat.chat_consumer_service.service.IncomingMessagesImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Configuration
public class ConsumerConfiguration {

    @Bean
    public MessageReceiver messageReceiver(SimpMessagingTemplate simpMessagingTemplate) {
        return new IncomingMessagesImpl(simpMessagingTemplate);
    }
}
