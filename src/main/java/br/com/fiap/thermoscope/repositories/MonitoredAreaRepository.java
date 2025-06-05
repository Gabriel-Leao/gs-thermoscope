package br.com.fiap.thermoscope.repositories;

import br.com.fiap.thermoscope.entities.MonitoredArea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MonitoredAreaRepository extends JpaRepository<MonitoredArea, UUID> {
}
