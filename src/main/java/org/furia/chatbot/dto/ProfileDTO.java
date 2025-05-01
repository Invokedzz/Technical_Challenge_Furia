package org.furia.chatbot.dto;


import java.time.LocalDateTime;

public record ProfileDTO (

        String username,

        String email,

        LocalDateTime createdAt

) {}
