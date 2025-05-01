package org.furia.chatbot.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "ChatOwnerInfo Data Transfer Object")
public record ChatOwnerInfoDTO (

        @Schema(example = "Peter Parker")
        String username

) {}
