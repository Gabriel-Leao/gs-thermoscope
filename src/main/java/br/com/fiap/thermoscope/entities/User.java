package br.com.fiap.thermoscope.entities;

import br.com.fiap.thermoscope.dtos.user.request.UpsertUserRequest;
import br.com.fiap.thermoscope.models.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    @Setter
    private String name;

    @Column(unique = true, nullable = false)
    @Setter
    private String email;

    @Column(nullable = false)
    @Setter
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public User(UpsertUserRequest createUserRequest) {
        this.name = createUserRequest.getName();
        this.email = createUserRequest.getEmail();
        this.password = createUserRequest.getPassword();
        this.role = convertStringToRole(createUserRequest.getRole());
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public UserRole convertStringToRole(String role) {
        return role != null ? UserRole.valueOf(role.toUpperCase()) : UserRole.FIREFIGHTER;
    }

    public void setRole(String role) {
        this.role = convertStringToRole(role);
    }
}
