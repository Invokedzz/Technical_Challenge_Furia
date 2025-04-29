package org.furia.chatbot.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginDTO (

        @NotBlank(message = "Username must not be blank!")
        @Size(min = 4, max = 30, message = "Username length must be higher than 4 and lower than 30!")
        String username,

        @NotBlank(message = "Password must not be blank!")
        @Size(min = 6, max = 20, message = "Password length must be higher than 6 and lower than 20!")
        String password

) {}
