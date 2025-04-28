package org.furia.chatbot.controller;

import org.furia.chatbot.services.ChatServices;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
public record ChatController (ChatServices chatServices) {
}
