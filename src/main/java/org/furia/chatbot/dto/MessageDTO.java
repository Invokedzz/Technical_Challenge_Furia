package org.furia.chatbot.dto;

import java.time.LocalDateTime;

public record MessageDTO (

        String message,

        LocalDateTime createdAt,

        ChatOwnerInfoDTO chatOwnerInfoDTO

) {}
