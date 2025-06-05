package br.com.fiap.thermoscope.repositories;

import br.com.fiap.thermoscope.entities.HeatSource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface HeatSourceRepository extends JpaRepository<HeatSource, UUID> {
    List<HeatSource> findByAreaId(UUID areaId);
}
