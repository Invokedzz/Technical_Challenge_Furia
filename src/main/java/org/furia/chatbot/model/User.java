package org.furia.chatbot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.furia.chatbot.dto.RegisterDTO;
import org.furia.chatbot.dto.UpdateUserDTO;

import java.time.LocalDateTime;
import java.util.List;

@Data @Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String email;

    private String password;

    private LocalDateTime createdAt;

    private Boolean active;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Roles roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List <Chat> chat;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List <Message> messages;

    public User (RegisterDTO registerDTO) {

        this.username = registerDTO.username();

        this.email = registerDTO.email();

        this.password = registerDTO.password();

        this.createdAt = LocalDateTime.now();

        this.active = true;

    }

    public void update (UpdateUserDTO updateUserDTO) {

        if (updateUserDTO.username() != null) {

            this.username = updateUserDTO.username();

        }

        if (updateUserDTO.email() != null) {

            this.email = updateUserDTO.email();

        }

        if (updateUserDTO.password() != null) {

            this.password = updateUserDTO.password();

        }

    }

    public void deactivate () {

        this.active = false;

    }

    public void reactivate () {

        this.active = true;

    }

}
