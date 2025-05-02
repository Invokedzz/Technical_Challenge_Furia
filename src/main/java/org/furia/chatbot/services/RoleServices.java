package org.furia.chatbot.services;

import lombok.RequiredArgsConstructor;
import org.furia.chatbot.model.Roles;
import org.furia.chatbot.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServices {

    private final RoleRepository roleRepository;

    public void createRole () {

        roleRepository.save(new Roles(1L, "ROLE_CLIENT"));

    }

    public void insertRoleInUser (Long userId) {

        roleRepository.insertRole(userId, 1L);

    }

}
