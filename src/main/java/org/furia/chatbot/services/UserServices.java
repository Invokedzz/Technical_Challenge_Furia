package org.furia.chatbot.services;

import lombok.RequiredArgsConstructor;
import org.furia.chatbot.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServices {

    private final UserRepository userRepository;

}
