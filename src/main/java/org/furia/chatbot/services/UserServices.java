package org.furia.chatbot.services;

import lombok.RequiredArgsConstructor;
import org.furia.chatbot.dto.RegisterDTO;
import org.furia.chatbot.model.User;
import org.furia.chatbot.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServices {

    private final UserRepository userRepository;

    public void register (RegisterDTO registerDTO) {

        userRepository.save(new User(registerDTO));

    }

    public void login () {}

    public void updateById () {}

    public void deleteById () {}

}
