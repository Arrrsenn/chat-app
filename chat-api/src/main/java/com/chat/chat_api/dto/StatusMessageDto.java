package com.chat.chat_api.dto;

import lombok.Builder;

@Builder
public record StatusMessageDto(boolean isShipped) {
}
