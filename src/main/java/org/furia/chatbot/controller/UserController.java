package org.furia.chatbot.controller;

import jakarta.validation.Valid;
import org.furia.chatbot.dto.*;
import org.furia.chatbot.services.UserServices;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public record UserController (UserServices userServices) {

    @PostMapping("/register")
    public ResponseEntity <Void> register (@Valid @RequestBody RegisterDTO registerDTO) {

        userServices.register(registerDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @PostMapping("/login")
    public ResponseEntity <TokenDTO> login (@Valid @RequestBody LoginDTO loginDTO) {

        var token = userServices.login(loginDTO);

        return ResponseEntity.status(HttpStatus.OK).body(new TokenDTO(token));

    }

    @GetMapping
    public ResponseEntity <ProfileDTO> profile (@RequestHeader HttpHeaders headers) {

        var profile = userServices.profile(headers);

        return ResponseEntity.status(HttpStatus.OK).body(profile);

    }

    @PutMapping("/update")
    public ResponseEntity <SuccessRespDTO> updateById (@RequestHeader HttpHeaders headers,
                                             @Valid @RequestBody UpdateUserDTO updateUserDTO) {

        userServices.updateUserById(headers, updateUserDTO);

        return ResponseEntity.status(HttpStatus.OK).body(new SuccessRespDTO("Conta atualizada com sucesso!"));

    }

    @PutMapping("/reactivate")
    public ResponseEntity <SuccessRespDTO> reactivateByEmail (@Valid @RequestBody ReactivateAccountDTO reactivateAccountDTO) {

        userServices.reactivateUserById(reactivateAccountDTO);

        return ResponseEntity.status(HttpStatus.OK).body(new SuccessRespDTO("Conta reativada com sucesso!"));

    }

    @DeleteMapping("/delete")
    public ResponseEntity <Void> deleteById (@RequestHeader HttpHeaders headers) {

        userServices.deactivateUserById(headers);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}
