package org.furia.chatbot.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(name = "Message Data Transfer Object")
public record MessageDTO (

        @Schema(example = "Me explique sobre a FURIA!")
        String message,

        @Schema(example = "2007-12-03T10:15:30")
        LocalDateTime createdAt,

        @Schema(example = "Peter Parker")
        ChatOwnerInfoDTO chatOwnerInfoDTO

) {}
