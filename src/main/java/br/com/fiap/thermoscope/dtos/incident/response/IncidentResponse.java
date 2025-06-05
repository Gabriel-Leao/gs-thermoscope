package br.com.fiap.thermoscope.dtos.incident.response;

import br.com.fiap.thermoscope.entities.Incident;
import br.com.fiap.thermoscope.models.enums.IncidentStatus;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class IncidentResponse {
    private final UUID id;
    private final UUID heatSourceId;
    private final UUID teamId;
    private final UUID userId;
    private final IncidentStatus status;
    private final LocalDateTime responseTime;
    private final LocalDate conclusionDate;
    private final String report;

    public IncidentResponse(Incident incident) {
        this.id = incident.getId();
        this.heatSourceId = incident.getHeatSource().getId();
        this.teamId = incident.getTeam().getId();
        this.userId = incident.getUser().getId();
        this.status = incident.getStatus();
        this.responseTime = incident.getResponseTime();
        this.conclusionDate = incident.getConclusionDate();
        this.report = incident.getReport();
    }
}
