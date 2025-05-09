package org.furia.chatbot.repository;

import org.furia.chatbot.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository <Message, Long> {



}
