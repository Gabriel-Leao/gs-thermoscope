package br.com.fiap.thermoscope.services;

import br.com.fiap.thermoscope.dtos.team.request.UpsertTeamRequest;
import br.com.fiap.thermoscope.dtos.team.response.TeamResponse;
import br.com.fiap.thermoscope.entities.Team;
import br.com.fiap.thermoscope.exceptions.NotFoundException;
import br.com.fiap.thermoscope.repositories.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository repository;

    public List<TeamResponse> findAll() {
        return repository.findAll().stream().map(TeamResponse::new).toList();
    }

    public TeamResponse findById(UUID id) {
        return repository.findById(id).map(TeamResponse::new)
                .orElseThrow(() -> new NotFoundException("Team not found"));
    }

    public TeamResponse create(UpsertTeamRequest request) {
        Team team = new Team(request);
        return new TeamResponse(repository.save(team));
    }

    public TeamResponse update(UUID id, UpsertTeamRequest request) {
        Team team = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Team not found"));

        team.setName(request.getName());
        team.setLocation(request.getLocation());
        team.setStatus(request.getStatus());
        return new TeamResponse(repository.save(team));
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
