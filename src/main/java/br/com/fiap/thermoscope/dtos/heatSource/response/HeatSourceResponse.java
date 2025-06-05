package br.com.fiap.thermoscope.dtos.heatSource.response;

import br.com.fiap.thermoscope.entities.HeatSource;
import br.com.fiap.thermoscope.models.enums.ConfirmedHeatSource;
import br.com.fiap.thermoscope.models.enums.Level;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class HeatSourceResponse {
    private final UUID id;
    private final UUID areaId;
    private final String areaName;
    private final Level intensity;
    private final ConfirmedHeatSource confirmed;
    private final LocalDateTime detectionTime;

    public HeatSourceResponse(HeatSource heatSource) {
        this.id = heatSource.getId();
        this.areaId = heatSource.getArea().getId();
        this.areaName = heatSource.getArea().getName();
        this.intensity = heatSource.getIntensity();
        this.confirmed = heatSource.getConfirmed();
        this.detectionTime = heatSource.getDetectionTime();
    }
}
