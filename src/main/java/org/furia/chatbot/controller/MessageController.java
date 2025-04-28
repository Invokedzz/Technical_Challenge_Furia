package org.furia.chatbot.controller;

import org.furia.chatbot.services.MessageServices;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat/messages")
public record MessageController (MessageServices messageServices) {
}
