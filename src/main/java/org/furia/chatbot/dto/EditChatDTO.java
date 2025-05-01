package org.furia.chatbot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(name = "EditChat Data Transfer Object")
public record EditChatDTO(

        @Schema(example = "Editando o chat")
        @NotBlank(message = "Title field cannot be blank!")
        @Size(min = 3, max = 50, message = "Title length must be higher than 3 and lower than 50!")
        String title,

        @Schema(example = "Chat editado")
        @Size(max = 100, message = "Description field cannot exceed 100 characters!")
        String description

) {}
