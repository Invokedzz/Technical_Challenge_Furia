package org.furia.chatbot.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateChatDTO (

        @NotBlank(message = "Title field cannot be blank!")
        @Size(min = 3, max = 50, message = "Title length must be higher than 3 and lower than 50!")
        String title,

        @Size(max = 100, message = "Description field cannot exceed 100 characters!")
        String description

) {}
