package com.chat.chat_producer_service.configuration;

import com.chat.chat_api.model.ChatMessage;
import com.chat.chat_api.service.MessageSender;
import com.chat.chat_producer_service.service.OutgoingMessagesServiceImpl;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class ProducerConfiguration {

    @Value("${spring.kafka.topic.name}")
    private String topicName;

    @Bean
    public NewTopic topic() {
        return TopicBuilder.name(topicName)
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public MessageSender messageService(KafkaTemplate<String, ChatMessage> kafkaTemplate) {
        return new OutgoingMessagesServiceImpl(kafkaTemplate);
    }
}
