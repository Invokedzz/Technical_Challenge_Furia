package org.furia.chatbot.repository;

import org.furia.chatbot.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository <Roles, Long> {



}
