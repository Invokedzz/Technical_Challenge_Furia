package org.furia.chatbot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.furia.chatbot.model.Chat;

import java.time.LocalDateTime;

@Schema(name = "Chat Data Transfer Object")
public record ChatDTO (

        @Schema(example = "Curiosidades sobre a FURIA")
        String title,

        @Schema(example = "Curiosidades")
        String description,

        @Schema(example = "2007-12-03T10:15:30")
        LocalDateTime updatedAt,

        @Schema(example = "Peter Parker")
        ChatOwnerInfoDTO creator

) {

    public ChatDTO (Chat chat) {

        this (chat.getTitle(), chat.getDescription(), chat.getUpdatedAt(), new ChatOwnerInfoDTO(chat.getUser().getUsername()));

    }

}
