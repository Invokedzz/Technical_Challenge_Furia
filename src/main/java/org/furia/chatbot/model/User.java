package org.furia.chatbot.model;

import jakarta.persistence.*;
import lombok.*;
import org.furia.chatbot.dto.RegisterDTO;
import org.furia.chatbot.dto.EditUserDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private LocalDateTime createdAt;

    private Boolean active;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List <Roles> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List <Chat> chat;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List <Message> messages;

    public User (RegisterDTO registerDTO) {

        this.username = registerDTO.username();

        this.email = registerDTO.email();

        this.password = registerDTO.password();

        this.createdAt = LocalDateTime.now();

        this.active = true;

    }

    public void update (EditUserDTO updateUserDTO) {

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());

    }

}
