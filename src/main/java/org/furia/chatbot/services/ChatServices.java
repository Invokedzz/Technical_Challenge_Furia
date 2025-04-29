package org.furia.chatbot.services;

import lombok.RequiredArgsConstructor;
import org.furia.chatbot.repository.ChatRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatServices {

    private final ChatRepository chatRepository;

    public void createChat () {}

    public void chatList () {}

    public void findChatById (Long id) {}

    public void updateChatById (Long id) {}

    public void deleteChatById (Long id) {}

}
