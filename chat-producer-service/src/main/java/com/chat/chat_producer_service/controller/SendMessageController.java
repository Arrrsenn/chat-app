package com.chat.chat_producer_service.controller;

import com.chat.chat_api.dto.SendMessageRequest;
import com.chat.chat_api.dto.StatusMessageDto;
import com.chat.chat_api.service.MessageSender;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1")
@RestController
public class SendMessageController {

    private final MessageSender messageSender;

    public SendMessageController(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    @PostMapping("/send")
    public ResponseEntity<StatusMessageDto> sendMessage(@RequestBody SendMessageRequest sendMessageRequest) {
        messageSender.sendMessage(sendMessageRequest);
        return ResponseEntity.ok(
                StatusMessageDto
                        .builder()
                        .isShipped(true)
                        .build()
        );
    }
}
