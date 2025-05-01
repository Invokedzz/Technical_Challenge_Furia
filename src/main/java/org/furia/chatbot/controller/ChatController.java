package org.furia.chatbot.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.furia.chatbot.dto.ChatDTO;
import org.furia.chatbot.dto.CreateChatDTO;
import org.furia.chatbot.dto.EditChatDTO;
import org.furia.chatbot.dto.SuccessRespDTO;
import org.furia.chatbot.services.ChatServices;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
@Tag(name = "Chat Controller", description = "Controller criado para criação/visualização de chats")
public record ChatController (ChatServices chatServices) {

    @PostMapping
    private ResponseEntity <SuccessRespDTO> createChat (@Valid @RequestBody CreateChatDTO createChatDTO,
                                                        @RequestHeader HttpHeaders headers){

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
    private ResponseEntity <List<ChatDTO>> findChatById (@RequestHeader HttpHeaders headers,
                                                         @PathVariable Long id) {

        var chat = chatServices.findChatById(headers, id);

        return ResponseEntity.status(HttpStatus.OK).body(chat);

    }

    @PutMapping("/{id}/update")
    private ResponseEntity <Void> updateChatById (@Valid @RequestBody EditChatDTO editChatDTO,
                                                  @RequestHeader HttpHeaders headers,
                                                  @PathVariable Long id) {

        chatServices.editChatById(editChatDTO, headers, id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @DeleteMapping("/{id}/delete")
    private ResponseEntity <Void> deleteChatById (@RequestHeader HttpHeaders headers,
                                                  @PathVariable Long id) {

        chatServices.deleteChatById(headers, id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}
