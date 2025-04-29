package org.furia.chatbot.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.RequiredArgsConstructor;
import org.furia.chatbot.exceptions.InvalidTokenException;
import org.furia.chatbot.exceptions.TokenCreationException;
import org.furia.chatbot.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TokenAuthService {

    @Value("spring.security.oauth2.client.registration")
    private String secret;

    public String generateLoginToken (User user) {

        List<String> userRoles = user.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        try {

            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.create()
                    .withIssuer("FURIA")
                    .withSubject(user.getUsername())
                    .withClaim("User Id", user.getId())
                    .withClaim("Roles", userRoles)
                    .withClaim("Is Active", user.getActive())
                    .withExpiresAt(tokenExpirationInstantForLoggedUser())
                    .sign(algorithm);

        } catch (JWTCreationException ex) {

            throw new TokenCreationException(ex.getMessage());

        }

    }


    public String verifyToken (String token) {

        try {

            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.require(algorithm)
                    .withIssuer("SwiftPayments")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException ex) {

            throw new InvalidTokenException(ex.getMessage());

        }

    }

    public Long findSessionId (HttpHeaders headers) {

        String token = Objects.requireNonNull(headers.get("Authorization")).getFirst();

        String jwt = token.replace("Bearer ", "");

        return JWT.decode(jwt).getClaim("User Id").asLong();

    }

    private Instant tokenExpirationInstantForLoggedUser () {

        return Instant.now().plusSeconds(3600);

    }

}
