package org.furia.chatbot.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(name = "Profile Data Transfer Object")
public record ProfileDTO (

        @Schema(example = "Peter Parker")
        String username,

        @Schema(example = "peterparker@gmail.com")
        String email,

        @Schema(example = "2007-12-03T10:15:30")
        LocalDateTime createdAt

) {}
