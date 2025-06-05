package br.com.fiap.thermoscope.entities;

import br.com.fiap.thermoscope.dtos.team.request.UpsertTeamRequest;
import br.com.fiap.thermoscope.models.enums.TeamStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "teams")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    @Setter
    private String name;

    @Column(nullable = false)
    @Setter
    private String location;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TeamStatus status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Team(UpsertTeamRequest request) {
        this.name = request.getName();
        this.location = request.getLocation();
        this.status = convertStringToStatus(request.getStatus());
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

    public TeamStatus convertStringToStatus(String role) {
        return role != null ? TeamStatus.valueOf(role.toUpperCase()) : TeamStatus.AVAILABLE;
    }

    public void setStatus(String status) {
        this.status = convertStringToStatus(status);
    }
}
