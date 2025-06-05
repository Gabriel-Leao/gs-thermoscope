package br.com.fiap.thermoscope.services;

import br.com.fiap.thermoscope.dtos.monitoredArea.request.UpsertMonitoredAreaRequest;
import br.com.fiap.thermoscope.dtos.monitoredArea.response.MonitoredAreaResponse;
import br.com.fiap.thermoscope.entities.MonitoredArea;
import br.com.fiap.thermoscope.exceptions.NotFoundException;
import br.com.fiap.thermoscope.repositories.MonitoredAreaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MonitoredAreaService {
    private final MonitoredAreaRepository repository;

    public MonitoredAreaResponse create(UpsertMonitoredAreaRequest request) {
        MonitoredArea area = new MonitoredArea(request);
        return new MonitoredAreaResponse(repository.save(area));
    }

    public List<MonitoredAreaResponse> findAll() {
        return repository.findAll().stream().map(MonitoredAreaResponse::new).toList();
    }

    public MonitoredAreaResponse findById(UUID id) {
        MonitoredArea area = repository.findById(id).orElseThrow(() -> new NotFoundException("Area not found"));
        return new MonitoredAreaResponse(area);
    }

    public MonitoredAreaResponse update(UUID id, UpsertMonitoredAreaRequest request) {
        MonitoredArea area = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Area not found"));
        area.setName(request.getName());
        area.setDescription(request.getDescription());
        area.setRisk(request.getRisk());
        return new MonitoredAreaResponse(repository.save(area));
    }

    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Area not found");
        }
        repository.deleteById(id);
    }
}
