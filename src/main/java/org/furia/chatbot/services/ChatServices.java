package org.furia.chatbot.services;

import lombok.RequiredArgsConstructor;
import org.furia.chatbot.repository.ChatRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatServices {

    private final ChatRepository chatRepository;

}
