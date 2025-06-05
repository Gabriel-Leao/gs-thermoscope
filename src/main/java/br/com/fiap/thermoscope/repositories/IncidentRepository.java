package br.com.fiap.thermoscope.repositories;

import br.com.fiap.thermoscope.entities.Incident;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface IncidentRepository extends JpaRepository<Incident, UUID> {
    List<Incident> findByStatus(String status);

    List<Incident> findByHeatSourceId(UUID heatSourceId);

    List<Incident> findByTeamId(UUID teamId);
}
