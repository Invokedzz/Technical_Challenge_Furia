package org.furia.chatbot.services;

import lombok.RequiredArgsConstructor;
import org.furia.chatbot.config.OllamaConfig;
import org.furia.chatbot.dto.ChatOwnerInfoDTO;
import org.furia.chatbot.dto.CreateMessageDTO;
import org.furia.chatbot.dto.MessageDTO;
import org.furia.chatbot.exceptions.BadRequestException;
import org.furia.chatbot.exceptions.NotFoundException;
import org.furia.chatbot.model.Chat;
import org.furia.chatbot.model.Message;
import org.furia.chatbot.model.User;
import org.furia.chatbot.repository.MessageRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServices {

    private final MessageRepository messageRepository;

    private final ChatServices chatServices;

    private final OllamaConfig ollamaConfig;

    private final AuthService authService;

    private final UserServices userServices;

    private final TokenAuthService tokenAuthService;

    public String createMessage (HttpHeaders headers, CreateMessageDTO messageDTO) {

        var chat = findChatById(messageDTO.chatId());

        authService.compareIdFromTheSessionWithTheIdInTheUrl(headers, messageDTO.userId());

        var user = findUserById(messageDTO.userId());

        var response = ollamaConfig.getAIResponse(messageDTO.message());

        messageRepository.save(new Message(messageDTO, response, chat, user));

        return response;

    }

    public List <MessageDTO> allMessages (HttpHeaders headers) {

        Long userId = getUserId(headers);

        return messageRepository
                .findAll()
                .stream()
                .filter(x -> x.getUser().getId().equals(userId))
                .map(MessageDTO::new)
                .toList();

    }

    public MessageDTO findMessageById (HttpHeaders headers, Long id) {

        Long userId = getUserId(headers);

        var message = findMessageById(id);

        if (!message.getUser().getId().equals(userId)) {

            throw new BadRequestException("Id da sessão não relacionado com a mensagem!");

        }

        return new MessageDTO(message.getMessage(), message.getCreatedAt(), new ChatOwnerInfoDTO(message.getUser().getUsername()));

    }

    public void updateMessageById (CreateMessageDTO createMessageDTO, Long id) {

        var message = findMessageById(id);

        message.setMessage(createMessageDTO.message());

        messageRepository.save(message);

    }

    public void deleteMessageById (HttpHeaders headers, Long id) {

        Long userId = getUserId(headers);

        var message = findMessageById(id);

        if (message.getUser().getId().equals(userId)) {

            messageRepository.delete(message);

        }

    }

    private Message findMessageById (Long id) {

        return messageRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Mensagem não encontrada!"));

    }

    private Long getUserId (HttpHeaders headers) {

        return tokenAuthService.findSessionId(headers);

    }

    private User findUserById (Long id) {

        return userServices.findUserById(id);

    }

    private Chat findChatById (Long id) {

        return chatServices.findChatById(id);

    }

}
