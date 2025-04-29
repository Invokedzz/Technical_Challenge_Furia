package org.furia.chatbot.dto;

import org.furia.chatbot.model.Chat;

import java.time.LocalDateTime;

public record ChatDTO (String title, String description, LocalDateTime updatedAt, ChatOwnerInfoDTO creator) {

    public ChatDTO (Chat chat) {

        this (chat.getTitle(), chat.getDescription(), chat.getUpdatedAt(), new ChatOwnerInfoDTO(chat.getUser().getUsername()));

    }

}
