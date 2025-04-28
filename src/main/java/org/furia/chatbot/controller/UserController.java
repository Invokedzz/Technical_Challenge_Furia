package org.furia.chatbot.controller;

import jakarta.validation.Valid;
import org.furia.chatbot.dto.ProfileDTO;
import org.furia.chatbot.dto.RegisterDTO;
import org.furia.chatbot.dto.UpdateUserDTO;
import org.furia.chatbot.services.UserServices;
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

    @GetMapping("/{id}")
    public ResponseEntity <ProfileDTO> profile (@PathVariable Long id) {

        var profile = userServices.profile(id);

        return ResponseEntity.status(HttpStatus.OK).body(profile);

    }

    @PutMapping("/{id}/update")
    public ResponseEntity <Void> updateById (@PathVariable Long id,
                                             @Valid @RequestBody UpdateUserDTO updateUserDTO) {

        userServices.updateById(id, updateUserDTO);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @PutMapping("/{id}/reactivate")
    public ResponseEntity <Void> reactivateById (@PathVariable Long id) {

        userServices.reactivateById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity <Void> deleteById (@PathVariable Long id) {

        userServices.deactivateById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}
