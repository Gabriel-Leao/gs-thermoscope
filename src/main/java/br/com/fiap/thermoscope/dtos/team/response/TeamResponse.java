package br.com.fiap.thermoscope.dtos.team.response;

import br.com.fiap.thermoscope.entities.Team;
import br.com.fiap.thermoscope.models.enums.TeamStatus;
import lombok.Getter;

import java.util.UUID;

@Getter
public class TeamResponse {
    private final UUID id;
    private final String name;
    private final String location;
    private final TeamStatus status;

    public TeamResponse(Team team) {
        this.id = team.getId();
        this.name = team.getName();
        this.location = team.getLocation();
        this.status = team.getStatus();
    }
}

