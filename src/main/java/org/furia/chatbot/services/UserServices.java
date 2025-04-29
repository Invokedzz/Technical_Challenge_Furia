package org.furia.chatbot.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.furia.chatbot.dto.LoginDTO;
import org.furia.chatbot.dto.ProfileDTO;
import org.furia.chatbot.dto.RegisterDTO;
import org.furia.chatbot.dto.UpdateUserDTO;
import org.furia.chatbot.exceptions.NotFoundException;
import org.furia.chatbot.model.User;
import org.furia.chatbot.repository.UserRepository;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServices {

    private final UserRepository userRepository;

    private final RoleServices roleServices;

    private final AuthService authService;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void register (RegisterDTO registerDTO) {

        var encodedPassword = passwordEncoder.encode(registerDTO.password());

        var user = new User(registerDTO);

        user.setPassword(encodedPassword);

        userRepository.save(user);

        roleServices.insertRoleInUser(user.getId());

    }

    public String login (LoginDTO loginDTO) {

        var user = userRepository.findByUsername(loginDTO.username());

        if (user != null) {

            return authService.validateLoginPropertiesThenGenerateToken(loginDTO);

        }

        throw new NotFoundException("User not found!");

    }

    public ProfileDTO profile (Long id) {

        var user = findUserById(id);

        return new ProfileDTO(user.getUsername(), user.getEmail());

    }

    public void updateUserById (Long id, UpdateUserDTO updateUserDTO) {

        var user = findUserById(id);

        user.update(updateUserDTO);

        userRepository.save(user);

    }

    public void reactivateUserById (Long id) {

        var user = findUserById(id);

        user.reactivate();

        userRepository.save(user);

    }

    public void deactivateUserById (Long id) {

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
