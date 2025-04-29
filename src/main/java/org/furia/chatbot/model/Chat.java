package org.furia.chatbot.model;

import jakarta.persistence.*;
import lombok.*;
import org.furia.chatbot.dto.CreateChatDTO;
import org.furia.chatbot.dto.EditChatDTO;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
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

        this.user = user;

        this.active = true;

    }

    public void update (EditChatDTO updateChatDTO) {

        if (updateChatDTO.title() != null) {

            this.title = updateChatDTO.title();

        }

        if (updateChatDTO.description() != null) {

            this.description = updateChatDTO.description();

        }

    }

}
