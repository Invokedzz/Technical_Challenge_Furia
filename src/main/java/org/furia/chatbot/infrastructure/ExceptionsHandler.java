package org.furia.chatbot.infrastructure;

import org.furia.chatbot.dto.ErrorDTO;
import org.furia.chatbot.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity <ErrorDTO> handleMethodArgumentNotValidException (MethodArgumentNotValidException ex) {

        FieldError fieldError = ex.getBindingResult().getFieldError();

        assert fieldError != null;

        ErrorDTO response = new ErrorDTO(

                HttpStatus.BAD_REQUEST.value(),
                fieldError.getDefaultMessage(),
                LocalDateTime.now()

        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity <ErrorDTO> handleNotFoundException (NotFoundException ex) {

        ErrorDTO response = new ErrorDTO(

                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                LocalDateTime.now()

        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

    }

}
