package org.furia.chatbot.services;

import lombok.RequiredArgsConstructor;
import org.furia.chatbot.dto.*;
import org.furia.chatbot.exceptions.BadRequestException;
import org.furia.chatbot.exceptions.NotFoundException;
import org.furia.chatbot.model.Roles;
import org.furia.chatbot.model.User;
import org.furia.chatbot.repository.RoleRepository;
import org.furia.chatbot.repository.UserRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServices {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    private final TokenAuthService tokenAuthService;

    private final AuthService authService;

    public void register (RegisterDTO registerDTO) {

        var encodedPassword = passwordEncoder.encode(registerDTO.password());

        var user = new User(registerDTO);

        checkIfEmailExists(user.getEmail());

       checkIfUsernameExists(user.getUsername());

        user.setPassword(encodedPassword);

        Roles role = roleRepository.findById(1L)
                .orElseGet(() -> roleRepository.save(new Roles(null, "ROLE_CLIENT")));
        user.setRoles(List.of(role));
        userRepository.save(user);

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

    private void checkIfEmailExists (String email) {

        if (findUserByEmail(email) != null) {

            throw new BadRequestException("Este Email já existe!");

        }

    }

    private void checkIfUsernameExists (String username) {

        if (userRepository.findByUsername(username) != null) {

            throw new BadRequestException("Este nome já existe!");

        }

    }

}
