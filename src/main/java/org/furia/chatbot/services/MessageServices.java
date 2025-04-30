package org.furia.chatbot.services;

import lombok.RequiredArgsConstructor;
import org.furia.chatbot.config.OllamaConfig;
import org.furia.chatbot.dto.MessageDTO;
import org.furia.chatbot.dto.ResponseDTO;
import org.furia.chatbot.model.Message;
import org.furia.chatbot.repository.MessageRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageServices {

    private final MessageRepository messageRepository;

    private final ChatServices chatServices;

    private final OllamaConfig ollamaConfig;

    private final AuthService authService;

    private final UserServices userServices;

    public String createMessage (HttpHeaders headers, MessageDTO messageDTO) {

        var chat = chatServices.findChatById(messageDTO.chatId());

        authService.compareIdFromTheSessionWithTheIdInTheUrl(headers, messageDTO.userId());

        var user = userServices.findUserById(messageDTO.userId());

        var response = ollamaConfig.getAIResponse(messageDTO.message());

        messageRepository.save(new Message(messageDTO, response, chat, user));

        return response;

    }

    public void allMessages () {



    }

    public void findMessageById (Long id) {



    }

    public void updateMessageById (Long id) {



    }

    public void deleteMessageById (Long id) {



    }

}
