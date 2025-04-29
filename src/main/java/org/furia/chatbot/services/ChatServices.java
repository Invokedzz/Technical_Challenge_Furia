package org.furia.chatbot.services;

import lombok.RequiredArgsConstructor;
import org.furia.chatbot.dto.ChatDTO;
import org.furia.chatbot.dto.CreateChatDTO;
import org.furia.chatbot.model.Chat;
import org.furia.chatbot.repository.ChatRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatServices {

    private final ChatRepository chatRepository;

    private final UserServices userServices;

    private final TokenAuthService tokenAuthService;

    public void createChat (HttpHeaders headers, CreateChatDTO createChatDTO) {

        Long userId = findUserIdInTheSession(headers);

        var user = userServices.findUserById(userId);

        chatRepository.save(new Chat(createChatDTO, user));

    }

    public List <ChatDTO> chatList (HttpHeaders headers) {

        Long userId = tokenAuthService.findSessionId(headers);

        return chatRepository
                .findAll()
                .stream()
                .filter(chat -> chat.getUser().getId().equals(userId))
                .map(ChatDTO::new)
                .collect(Collectors.toList());

    }

    public void findChatById (Long id) {}

    public void updateChatById (Long id) {}

    public void deleteChatById (Long id) {}

    private Long findUserIdInTheSession (HttpHeaders headers) {

        return tokenAuthService.findSessionId(headers);

    }

}
