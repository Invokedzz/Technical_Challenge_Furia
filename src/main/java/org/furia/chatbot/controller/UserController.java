package org.furia.chatbot.controller;

import org.furia.chatbot.services.UserServices;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public record UserController (UserServices userServices) {
}
