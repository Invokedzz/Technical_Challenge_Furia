package org.furia.chatbot.services;

import lombok.RequiredArgsConstructor;
import org.furia.chatbot.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServices {

    private final RoleRepository roleRepository;

}
