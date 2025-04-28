package org.furia.chatbot.dto;

import java.time.LocalDateTime;

public record ErrorDTO (

        Integer httpStatus,

        String message,

        LocalDateTime timestamp

) {}
