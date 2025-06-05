package br.com.fiap.thermoscope.controllers;

import br.com.fiap.thermoscope.dtos.monitoredArea.request.UpsertMonitoredAreaRequest;
import br.com.fiap.thermoscope.dtos.monitoredArea.response.MonitoredAreaResponse;
import br.com.fiap.thermoscope.services.MonitoredAreaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/areas")
@RequiredArgsConstructor
public class MonitoredAreaController {
    private final MonitoredAreaService service;

    @PostMapping
    public ResponseEntity<MonitoredAreaResponse> create(@RequestBody @Valid UpsertMonitoredAreaRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping
    public ResponseEntity<List<MonitoredAreaResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MonitoredAreaResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MonitoredAreaResponse> update(@PathVariable UUID id, @RequestBody @Valid UpsertMonitoredAreaRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
