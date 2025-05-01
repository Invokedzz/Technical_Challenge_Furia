package org.furia.chatbot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

@Schema(name = "EditUser Data Transfer Object")
public record EditUserDTO(

        @Schema(example = "Venom")
        @Size(min = 4, max = 30, message = "Username length must be higher than 4 and lower than 30!")
        String username,

        @Schema(example = "Venom@gmail.com")
        @Email(message = "Enter a valid email!")
        String email,

        @Schema(example = "betterThanSpiderman123")
        @Size(min = 6, max = 20, message = "Password length must be higher than 6 and lower than 20!")
        String password

) {}
