package org.furia.chatbot.dto;

import jakarta.validation.constraints.Email;

public record ReactivateAccountDTO (

        @Email(message = "Enter a valid email!")
        String email

) {}
