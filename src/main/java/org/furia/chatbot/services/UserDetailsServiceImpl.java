package org.furia.chatbot.services;

import lombok.RequiredArgsConstructor;
import org.furia.chatbot.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {

        var user = userRepository.findByUsername(username);

        if (user != null) {

            return user;

        }

        throw new UsernameNotFoundException("Não conseguimos encontrar um usuário com este nome:" + username);

    }

}
