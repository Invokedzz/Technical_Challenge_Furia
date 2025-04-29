package org.furia.chatbot.repository;

import org.furia.chatbot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {

    UserDetails findByUsername (String username);

    Optional <User> findByEmail(String email);
}
