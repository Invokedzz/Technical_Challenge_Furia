package org.furia.chatbot.services;

import lombok.RequiredArgsConstructor;
import org.furia.chatbot.dto.ProfileDTO;
import org.furia.chatbot.dto.RegisterDTO;
import org.furia.chatbot.dto.UpdateUserDTO;
import org.furia.chatbot.exceptions.NotFoundException;
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

    public ProfileDTO profile (Long id) {

        var user = findUserById(id);

        return new ProfileDTO(user.getUsername(), user.getEmail());

    }

    public void updateById (Long id, UpdateUserDTO updateUserDTO) {

        var user = findUserById(id);

        user.update(updateUserDTO);

        userRepository.save(user);

    }

    public void reactivateById (Long id) {

        var user = findUserById(id);

        user.reactivate();

        userRepository.save(user);

    }

    public void deactivateById (Long id) {

        var user = findUserById(id);

        user.deactivate();

        userRepository.save(user);

    }

    private User findUserById (Long id) {

       return userRepository
               .findById(id)
               .orElseThrow(() -> new NotFoundException("User not found!"));

    }

}
