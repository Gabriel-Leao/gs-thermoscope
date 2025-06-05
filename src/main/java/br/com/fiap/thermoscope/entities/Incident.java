package br.com.fiap.thermoscope.entities;

import br.com.fiap.thermoscope.dtos.incident.request.UpsertIncidentRequest;
import br.com.fiap.thermoscope.models.enums.IncidentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "incidents")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Incident {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "heat_source_id", nullable = false)
    @Setter
    private HeatSource heatSource;

    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    @Setter
    private Team team;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @Setter
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Setter
    private IncidentStatus status;

    @Column(name = "response_time")
    private LocalDateTime responseTime;

    @Column(name = "conclusion_date")
    @Setter
    private LocalDate conclusionDate;

    @Column(length = 500)
    @Setter
    private String report;

    public Incident(HeatSource heatSource, Team team, User user, UpsertIncidentRequest request) {
        this.heatSource = heatSource;
        this.team = team;
        this.user = user;
        this.status = IncidentStatus.PENDING;
        this.responseTime = LocalDateTime.now();
        this.report = request.getReport();
    }

    @PrePersist
    protected void onCreate() {
        responseTime = LocalDateTime.now();
    }

    public IncidentStatus convertStringToIncidentStatus(String status) {
        return status != null ? IncidentStatus.valueOf(status.toUpperCase()) : IncidentStatus.PENDING;
    }

    public void setStatus(String status) {
        this.status = convertStringToIncidentStatus(status);
    }
}
