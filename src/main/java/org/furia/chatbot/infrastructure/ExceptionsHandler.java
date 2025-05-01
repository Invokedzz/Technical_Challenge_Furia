package org.furia.chatbot.infrastructure;

import feign.FeignException;
import org.furia.chatbot.dto.ErrorDTO;
import org.furia.chatbot.exceptions.BadRequestException;
import org.furia.chatbot.exceptions.NotFoundException;
import org.furia.chatbot.exceptions.UnauthorizedException;
import org.furia.chatbot.exceptions.UnprocessableEntity;
import org.springframework.cloud.openfeign.FeignClient;
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

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity <ErrorDTO> handleBadRequestException (BadRequestException ex) {

        ErrorDTO response = new ErrorDTO(

                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                LocalDateTime.now()

        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity <ErrorDTO> handleUnauthorizedException (UnauthorizedException ex) {

        ErrorDTO response = new ErrorDTO(

                HttpStatus.UNAUTHORIZED.value(),
                ex.getMessage(),
                LocalDateTime.now()

        );

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);

    }

    @ExceptionHandler(UnprocessableEntity.class)
    public ResponseEntity <ErrorDTO> handleUnprocessableEntityException (UnprocessableEntity ex) {

        ErrorDTO response = new ErrorDTO(

                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                ex.getMessage(),
                LocalDateTime.now()

        );

        return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);

    }

}
