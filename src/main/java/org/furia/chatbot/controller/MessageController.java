package org.furia.chatbot.controller;

import jakarta.validation.Valid;
import org.furia.chatbot.dto.CreateMessageDTO;
import org.furia.chatbot.dto.MessageDTO;
import org.furia.chatbot.dto.ResponseDTO;
import org.furia.chatbot.services.MessageServices;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat/messages")
public record MessageController (MessageServices messageServices) {

    @PostMapping
    private ResponseEntity <ResponseDTO> createMessage (@RequestHeader HttpHeaders headers,
                                                        @Valid @RequestBody CreateMessageDTO messageDTO) {

        var response = messageServices.createMessage(headers, messageDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO(response));

    }

    @GetMapping
    private ResponseEntity <Void> allMessages () {

        messageServices.allMessages();

        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @GetMapping("/{id}")
    private ResponseEntity <MessageDTO> findMessageById (@RequestHeader HttpHeaders headers,
                                                         @PathVariable Long id) {

        var message = messageServices.findMessageById(headers, id);

        return ResponseEntity.status(HttpStatus.OK).body(message);

    }

    @PutMapping("/{id}/update")
    private ResponseEntity <Void> updateMessageById (@Valid @RequestBody CreateMessageDTO createMessageDTO,
                                                     @PathVariable Long id) {

        messageServices.updateMessageById(createMessageDTO, id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @DeleteMapping("/{id}/delete")
    private ResponseEntity <Void> deleteMessageById (@RequestHeader HttpHeaders headers,
                                                     @PathVariable Long id) {

        messageServices.deleteMessageById(headers, id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}
