package br.com.fiap.thermoscope.services;

import br.com.fiap.thermoscope.dtos.heatSource.request.UpsertHeatSourceRequest;
import br.com.fiap.thermoscope.dtos.heatSource.response.HeatSourceResponse;
import br.com.fiap.thermoscope.entities.HeatSource;
import br.com.fiap.thermoscope.entities.MonitoredArea;
import br.com.fiap.thermoscope.exceptions.NotFoundException;
import br.com.fiap.thermoscope.repositories.HeatSourceRepository;
import br.com.fiap.thermoscope.repositories.MonitoredAreaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HeatSourceService {
    private final HeatSourceRepository repository;
    private final MonitoredAreaRepository areaRepository;

    public HeatSourceResponse create(UpsertHeatSourceRequest request) {
        MonitoredArea area = areaRepository.findById(request.getAreaId())
                .orElseThrow(() -> new NotFoundException("Area not found"));

        HeatSource heatSource = new HeatSource(area, request);
        return new HeatSourceResponse(repository.save(heatSource));
    }

    public List<HeatSourceResponse> findAll() {
        return repository.findAll().stream().map(HeatSourceResponse::new).toList();
    }

    public HeatSourceResponse findById(UUID id) {
        HeatSource heatSource = repository.findById(id).orElseThrow(() -> new NotFoundException("Heat source not found"));
        return new HeatSourceResponse(heatSource);
    }

    public HeatSourceResponse update(UUID id, UpsertHeatSourceRequest request) {
        HeatSource heatSource = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Heat source not found"));

        MonitoredArea area = areaRepository.findById(request.getAreaId())
                .orElseThrow(() -> new NotFoundException("Area not found"));

        heatSource.setIntensity(request.getIntensity());
        heatSource.setConfirmed(request.getConfirmed());
        heatSource.setArea(area);

        return new HeatSourceResponse(repository.save(heatSource));
    }

    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Heat source not found");
        }
        repository.deleteById(id);
    }
}
