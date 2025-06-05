package br.com.fiap.thermoscope.entities;

import br.com.fiap.thermoscope.dtos.heatSource.request.UpsertHeatSourceRequest;
import br.com.fiap.thermoscope.models.enums.ConfirmedHeatSource;
import br.com.fiap.thermoscope.models.enums.Level;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "heat_sources")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HeatSource {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "area_id", nullable = false)
    @Setter
    private MonitoredArea area;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Level intensity;

    @Enumerated(EnumType.STRING)
    private ConfirmedHeatSource confirmed;

    @Column(name = "detection_time")
    private LocalDateTime detectionTime;

    public HeatSource(MonitoredArea area, UpsertHeatSourceRequest request) {
        this.area = area;
        this.intensity = convertStringToIntensity(request.getIntensity());
        this.confirmed = transformCharToConfirmedHeatSource(request.getConfirmed());
    }

    @PrePersist
    protected void onCreate() {
        detectionTime = LocalDateTime.now();
    }

    public Level convertStringToIntensity(String level) {
        return level != null ? Level.valueOf(level.toUpperCase()) : Level.LOW;
    }

    public void setIntensity(String intensity) {
        this.intensity = convertStringToIntensity(intensity);
    }

    public ConfirmedHeatSource transformCharToConfirmedHeatSource(String confirmed) {
        return confirmed.equalsIgnoreCase("Y") ? ConfirmedHeatSource.Y : ConfirmedHeatSource.N;
    }

    public void setConfirmed(String confirmed) {
        this.confirmed = transformCharToConfirmedHeatSource(confirmed);
    }
}
