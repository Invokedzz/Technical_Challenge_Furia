package org.furia.chatbot.controller;

import jakarta.validation.Valid;
import org.furia.chatbot.dto.RegisterDTO;
import org.furia.chatbot.services.UserServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public record UserController (UserServices userServices) {

    @PostMapping("/register")
    public ResponseEntity <Void> register (@Valid @RequestBody RegisterDTO registerDTO) {

        userServices.register(registerDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

}
