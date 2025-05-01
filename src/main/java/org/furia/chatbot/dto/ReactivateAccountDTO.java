package org.furia.chatbot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;

@Schema(name = "ReactivateAccount Data Transfer Object")
public record ReactivateAccountDTO (

        @Schema(name = "peterparker@gmail.com")
        @Email(message = "Enter a valid email!")
        String email

) {}
