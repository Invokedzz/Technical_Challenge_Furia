package org.furia.chatbot.services;

import lombok.RequiredArgsConstructor;
import org.furia.chatbot.repository.MessageRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageServices {

    private final MessageRepository messageRepository;

}
