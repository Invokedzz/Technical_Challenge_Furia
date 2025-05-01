package org.furia.chatbot.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.furia.chatbot.dto.*;
import org.furia.chatbot.services.UserServices;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@Tag(name = "User Controller", description = "Controller criado para criação/login de usuários")
public record UserController (UserServices userServices) {

    @PostMapping("/register")
    private ResponseEntity <Void> register (@Valid @RequestBody RegisterDTO registerDTO) {

        userServices.register(registerDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @PostMapping("/login")
    private ResponseEntity <TokenDTO> login (@Valid @RequestBody LoginDTO loginDTO) {

        var token = userServices.login(loginDTO);

        return ResponseEntity.status(HttpStatus.OK).body(new TokenDTO(token));

    }

    @GetMapping
    private ResponseEntity <ProfileDTO> profile (@RequestHeader HttpHeaders headers) {

        var profile = userServices.profile(headers);

        return ResponseEntity.status(HttpStatus.OK).body(profile);

    }

    @PutMapping("/update")
    private ResponseEntity <SuccessRespDTO> updateById (@RequestHeader HttpHeaders headers,
                                                        @Valid @RequestBody EditUserDTO updateUserDTO) {

        userServices.updateUserById(headers, updateUserDTO);

        return ResponseEntity.status(HttpStatus.OK).body(new SuccessRespDTO("Conta atualizada com sucesso!"));

    }

    @PutMapping("/reactivate")
    private ResponseEntity <SuccessRespDTO> reactivateByEmail (@Valid @RequestBody ReactivateAccountDTO reactivateAccountDTO) {

        userServices.reactivateUserById(reactivateAccountDTO);

        return ResponseEntity.status(HttpStatus.OK).body(new SuccessRespDTO("Conta reativada com sucesso!"));

    }

    @DeleteMapping("/delete")
    private ResponseEntity <Void> deleteById (@RequestHeader HttpHeaders headers) {

        userServices.deactivateUserById(headers);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}
