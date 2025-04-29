package org.furia.chatbot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.furia.chatbot.dto.CreateChatDTO;

import java.time.LocalDateTime;

@Data @Entity
@NoArgsConstructor
@AllArgsConstructor
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Chat (CreateChatDTO createChatDTO, User user) {

        this.title = createChatDTO.title();

        this.description = createChatDTO.description();

        this.createdAt = LocalDateTime.now();

        this.updatedAt = LocalDateTime.now();

        this.active = true;

        this.user = user;

    }

}
