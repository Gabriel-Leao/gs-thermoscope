package br.com.fiap.thermoscope.entities;

import br.com.fiap.thermoscope.dtos.monitoredArea.request.UpsertMonitoredAreaRequest;
import br.com.fiap.thermoscope.models.enums.Level;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "monitored_areas")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MonitoredArea {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    @Setter
    private String name;

    @Column()
    @Setter
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Level risk;

    public MonitoredArea(UpsertMonitoredAreaRequest request) {
        this.name = request.getName();
        this.description = request.getDescription();
        this.risk = convertStringToRisk(request.getRisk());
    }

    public Level convertStringToRisk(String level) {
        return level != null ? Level.valueOf(level.toUpperCase()) : Level.MEDIUM;
    }

    public void setRisk(String risk) {
        this.risk = convertStringToRisk(risk);
    }
}
