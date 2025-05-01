package org.furia.chatbot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(name = "Login Data Transfer Object")
public record LoginDTO (

        @Schema(example = "Peter Parker")
        @NotBlank(message = "Username must not be blank!")
        @Size(min = 4, max = 30, message = "Username length must be higher than 4 and lower than 30!")
        String username,

        @Schema(example = "SpiderMan123")
        @NotBlank(message = "Password must not be blank!")
        @Size(min = 6, max = 20, message = "Password length must be higher than 6 and lower than 20!")
        String password

) {}
