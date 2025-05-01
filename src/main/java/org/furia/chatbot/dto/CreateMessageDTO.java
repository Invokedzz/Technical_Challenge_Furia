package org.furia.chatbot.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "CreateMessage Data Transfer Object")
public record CreateMessageDTO (

        @Schema(example = "Fale sobre o elenco da FURIA")
        String message,

        @Schema(example = "1L")
        Long userId,

        @Schema(example = "2L")
        Long chatId

) {}
