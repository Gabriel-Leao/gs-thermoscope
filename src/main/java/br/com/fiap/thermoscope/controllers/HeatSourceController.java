package br.com.fiap.thermoscope.controllers;

import br.com.fiap.thermoscope.dtos.heatSource.request.UpsertHeatSourceRequest;
import br.com.fiap.thermoscope.dtos.heatSource.response.HeatSourceResponse;
import br.com.fiap.thermoscope.services.HeatSourceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/heatsources")
@RequiredArgsConstructor
public class HeatSourceController {
    private final HeatSourceService service;

    @PostMapping
    public ResponseEntity<HeatSourceResponse> create(@RequestBody @Valid UpsertHeatSourceRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping
    public ResponseEntity<List<HeatSourceResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HeatSourceResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HeatSourceResponse> update(@PathVariable UUID id, @RequestBody @Valid UpsertHeatSourceRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
