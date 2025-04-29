package org.furia.chatbot.services;

import lombok.RequiredArgsConstructor;
import org.furia.chatbot.dto.ChatDTO;
import org.furia.chatbot.dto.CreateChatDTO;
import org.furia.chatbot.dto.EditChatDTO;
import org.furia.chatbot.exceptions.NotFoundException;
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

    private final AuthService authService;

    private final TokenAuthService tokenAuthService;

    public void createChat (HttpHeaders headers, CreateChatDTO createChatDTO) {

        Long userId = findUserIdInTheSession(headers);

        var user = userServices.findUserById(userId);

        chatRepository.save(new Chat(createChatDTO, user));

    }

    public List <ChatDTO> chatList (HttpHeaders headers) {

        Long userId = findUserIdInTheSession(headers);

        return chatRepository
                .findAll()
                .stream()
                .filter(chat -> chat.getUser().getId().equals(userId))
                .map(ChatDTO::new)
                .collect(Collectors.toList());

    }

    public List <ChatDTO> findChatById (HttpHeaders headers, Long id) {

        Long userId = findUserIdInTheSession(headers);

        var chats = chatRepository.findByUser_Id(userId);

        var filterChatToMatchUserId = chats
                                        .stream()
                                        .filter(chat -> chat.getId().equals(id))
                                        .map(ChatDTO::new)
                                        .toList();

        if (filterChatToMatchUserId.isEmpty()) {

            throw new NotFoundException("Chat não encontrado!");

        }

        return filterChatToMatchUserId;

    }

    public void editChatById (EditChatDTO editChatDTO, HttpHeaders headers, Long id) {

        var chat = setupLogicForIdMethods(headers, id);

        chat.update(editChatDTO);

        chatRepository.save(chat);

    }

    public void deleteChatById (HttpHeaders headers, Long id) {

        var chat = setupLogicForIdMethods(headers, id);

        chatRepository.delete(chat);

    }

    private Chat setupLogicForIdMethods (HttpHeaders headers, Long id) {

        Long userId = findUserIdInTheSession(headers);

        authService.compareIdFromTheSessionWithTheIdInTheUrl(headers, userId);

        return chatRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Chat não encontrado!"));

    }

    private Long findUserIdInTheSession (HttpHeaders headers) {

        return tokenAuthService.findSessionId(headers);

    }

}
