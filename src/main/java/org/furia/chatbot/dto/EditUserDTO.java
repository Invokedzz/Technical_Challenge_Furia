package org.furia.chatbot.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record EditUserDTO(

        @Size(min = 4, max = 30, message = "Username length must be higher than 4 and lower than 30!")
        String username,

        @Email(message = "Enter a valid email!")
        String email,

        @Size(min = 6, max = 20, message = "Password length must be higher than 6 and lower than 20!")
        String password

) {}
