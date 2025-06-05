package br.com.fiap.thermoscope.services;

import br.com.fiap.thermoscope.dtos.incident.request.UpsertIncidentRequest;
import br.com.fiap.thermoscope.dtos.incident.response.IncidentResponse;
import br.com.fiap.thermoscope.entities.HeatSource;
import br.com.fiap.thermoscope.entities.Incident;
import br.com.fiap.thermoscope.entities.Team;
import br.com.fiap.thermoscope.entities.User;
import br.com.fiap.thermoscope.exceptions.NotFoundException;
import br.com.fiap.thermoscope.repositories.HeatSourceRepository;
import br.com.fiap.thermoscope.repositories.IncidentRepository;
import br.com.fiap.thermoscope.repositories.TeamRepository;
import br.com.fiap.thermoscope.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class IncidentService {
    private final IncidentRepository repository;
    private final HeatSourceRepository heatSourceRepository;
    private final TeamRepository teamRepository;
    private final UserRepository userRepository;

    public List<IncidentResponse> findAll() {
        return repository.findAll().stream().map(IncidentResponse::new).toList();
    }

    public IncidentResponse findById(UUID id) {
        return repository.findById(id).map(IncidentResponse::new)
                .orElseThrow(() -> new NotFoundException("Incident not found"));
    }

    public IncidentResponse create(UpsertIncidentRequest request) {
        HeatSource heatSource = heatSourceRepository.findById(request.getHeatSourceId())
                .orElseThrow(() -> new NotFoundException("Heat Source not found"));

        Team team = teamRepository.findById(request.getTeamId())
                .orElseThrow(() -> new NotFoundException("Team not found"));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new NotFoundException("User not found"));

        Incident incident = new Incident(heatSource, team, user, request);
        return new IncidentResponse(repository.save(incident));
    }

    public IncidentResponse updateIncident(UUID id, UpsertIncidentRequest request) {
        Incident incident = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Incident not found"));
        HeatSource heatSource = heatSourceRepository.findById(request.getHeatSourceId())
                .orElseThrow(() -> new NotFoundException("Heat Source not found"));
        Team team = teamRepository.findById(request.getTeamId())
                .orElseThrow(() -> new NotFoundException("Team not found"));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new NotFoundException("User not found"));

        incident.setStatus(request.getStatus());
        incident.setReport(request.getReport());
        incident.setConclusionDate(request.getConclusionDate());
        incident.setUser(user);
        incident.setHeatSource(heatSource);
        incident.setTeam(team);

        return new IncidentResponse(repository.save(incident));
    }

    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Incident not found");
        }
        repository.deleteById(id);
    }
}
