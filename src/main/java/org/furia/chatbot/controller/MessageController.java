package org.furia.chatbot.controller;

import org.furia.chatbot.services.MessageServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat/messages")
public record MessageController (MessageServices messageServices) {

    @PostMapping
    private ResponseEntity <Void> createMessage () {

        messageServices.createMessage();

        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @GetMapping
    private ResponseEntity <Void> allMessages () {

        messageServices.allMessages();

        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @GetMapping("/{id}")
    private ResponseEntity <Void> findMessageById (@PathVariable Long id) {

        messageServices.findMessageById(id);

        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @PutMapping("/{id}/update")
    private ResponseEntity <Void> updateMessageById (@PathVariable Long id) {

        messageServices.updateMessageById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @DeleteMapping("/{id}/delete")
    private ResponseEntity <Void> deleteMessageById (@PathVariable Long id) {

        messageServices.deleteMessageById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}
