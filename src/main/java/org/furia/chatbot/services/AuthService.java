package org.furia.chatbot.services;

import lombok.RequiredArgsConstructor;
import org.furia.chatbot.dto.LoginDTO;
import org.furia.chatbot.exceptions.ForbiddenAccessException;
import org.furia.chatbot.exceptions.InvalidTokenException;
import org.furia.chatbot.model.User;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;

    private final TokenAuthService tokenAuthService;

    public String validateLoginPropertiesThenGenerateToken (LoginDTO loginDTO) {

        var searchForUser = new UsernamePasswordAuthenticationToken(loginDTO.username(), loginDTO.password());

        var authentication = authenticationManager.authenticate(searchForUser);

        return tokenAuthService.generateLoginToken((User) authentication.getPrincipal());

    }

    public void compareIdFromTheSessionWithTheIdInTheUrl (HttpHeaders headers, Long sentId) {

        Long sessionId = tokenAuthService.findSessionId(headers);

        if (!sessionId.equals(sentId)) {

            throw new ForbiddenAccessException("Você não pode acessar esta sessão!");

        }

    }

}
