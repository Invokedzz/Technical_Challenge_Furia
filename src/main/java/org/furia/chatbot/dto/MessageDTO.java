package org.furia.chatbot.dto;

import org.furia.chatbot.model.Message;

import java.time.LocalDateTime;

public record MessageDTO (

        String message,

        LocalDateTime createdAt,

        ChatOwnerInfoDTO chatOwnerInfoDTO

) {

    public MessageDTO(Message message) {

        this (message.getMessage(), message.getCreatedAt(), new ChatOwnerInfoDTO(message.getUser().getUsername()));

    }

}
