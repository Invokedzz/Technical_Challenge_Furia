package org.furia.chatbot.controller;

import jakarta.validation.Valid;
import org.furia.chatbot.dto.CreateChatDTO;
import org.furia.chatbot.services.ChatServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
public record ChatController (ChatServices chatServices) {

    @PostMapping
    public ResponseEntity <Void> createChat (@Valid @RequestBody CreateChatDTO createChatDTO) {

        chatServices.createChat(createChatDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @GetMapping
    public ResponseEntity <Void> getAllChats () {

        chatServices.chatList();

        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @GetMapping("/{id}")
    public ResponseEntity <Void> findChatById (@PathVariable Long id) {

        chatServices.findChatById(id);

        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @PutMapping("/{id}/update")
    public ResponseEntity <Void> updateChatById (@PathVariable Long id) {

        chatServices.updateChatById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity <Void> deleteChatById (@PathVariable Long id) {

        chatServices.deleteChatById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}
