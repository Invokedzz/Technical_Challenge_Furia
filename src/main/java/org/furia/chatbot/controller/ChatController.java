package org.furia.chatbot.controller;

import jakarta.validation.Valid;
import org.furia.chatbot.dto.ChatDTO;
import org.furia.chatbot.dto.CreateChatDTO;
import org.furia.chatbot.dto.SuccessRespDTO;
import org.furia.chatbot.services.ChatServices;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
public record ChatController (ChatServices chatServices) {

    @PostMapping
    private ResponseEntity <SuccessRespDTO> createChat (@RequestHeader HttpHeaders headers,
                                                       @Valid @RequestBody CreateChatDTO createChatDTO) {

        chatServices.createChat(headers, createChatDTO);

        var response = new SuccessRespDTO("Chat criado com sucesso!");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @GetMapping
    private ResponseEntity <List<ChatDTO>> getAllChats (@RequestHeader HttpHeaders headers) {

        var chats = chatServices.chatList(headers);

        return ResponseEntity.status(HttpStatus.OK).body(chats);

    }

    @GetMapping("/{id}")
    private ResponseEntity <Void> findChatById (@PathVariable Long id) {

        chatServices.findChatById(id);

        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @PutMapping("/{id}/update")
    private ResponseEntity <Void> updateChatById (@PathVariable Long id) {

        chatServices.updateChatById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @DeleteMapping("/{id}/delete")
    private ResponseEntity <Void> deleteChatById (@PathVariable Long id) {

        chatServices.deleteChatById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}
