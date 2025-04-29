package org.furia.chatbot.model;

import jakarta.persistence.*;
import lombok.*;
import org.furia.chatbot.dto.MessageDTO;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    private String response;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "chat_id", nullable = false)
    private Chat chat;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Message (MessageDTO messageDTO, Chat chat, User user) {

        this.message = messageDTO.message();

        this.createdAt = LocalDateTime.now();

        this.chat = chat;

        this.user = user;

    }

    public void updateMessage (MessageDTO messageDTO) {

        if (messageDTO.message() != null) {

            this.message = messageDTO.message();

        }

    }

}
