package br.com.fiap.thermoscope.dtos.monitoredArea.response;

import br.com.fiap.thermoscope.entities.MonitoredArea;
import br.com.fiap.thermoscope.models.enums.Level;
import lombok.Getter;

import java.util.UUID;

@Getter
public class MonitoredAreaResponse {
    private final UUID id;
    private final String name;
    private final String description;
    private final Level risk;

    public MonitoredAreaResponse(MonitoredArea area) {
        this.id = area.getId();
        this.name = area.getName();
        this.description = area.getDescription();
        this.risk = area.getRisk();
    }
}
