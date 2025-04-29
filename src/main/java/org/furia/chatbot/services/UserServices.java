package org.furia.chatbot.services;

import lombok.RequiredArgsConstructor;
import org.furia.chatbot.dto.*;
import org.furia.chatbot.exceptions.NotFoundException;
import org.furia.chatbot.model.User;
import org.furia.chatbot.repository.UserRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServices {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final TokenAuthService tokenAuthService;

    private final RoleServices roleServices;

    private final AuthService authService;

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

        throw new NotFoundException("Usuário não encontrado!");

    }

    public ProfileDTO profile (HttpHeaders headers) {

        Long userId = findUserIdInTheSession(headers);

        var user = findUserById(userId);

        return new ProfileDTO(user.getUsername(), user.getEmail(), user.getCreatedAt());

    }

    public void updateUserById (HttpHeaders headers, EditUserDTO updateUserDTO) {

        Long userId = findUserIdInTheSession(headers);

        var user = findUserById(userId);

        user.update(updateUserDTO);

        userRepository.save(user);

    }

    public void reactivateUserById (ReactivateAccountDTO reactivateAccountDTO) {

        var user = findUserByEmail(reactivateAccountDTO.email());

        user.reactivate();

        userRepository.save(user);

    }

    public void deactivateUserById (HttpHeaders headers) {

        Long userId = findUserIdInTheSession(headers);

        var user = findUserById(userId);

        user.deactivate();

        userRepository.save(user);

    }

    private Long findUserIdInTheSession (HttpHeaders headers) {

        return tokenAuthService.findSessionId(headers);

    }

    protected User findUserById (Long id) {

       return userRepository
               .findById(id)
               .orElseThrow(() -> new NotFoundException("Usuário não encontrado!"));

    }

    private User findUserByEmail (String email) {

        return userRepository
                .findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado!"));

    }

}
