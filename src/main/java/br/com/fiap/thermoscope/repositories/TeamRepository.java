package br.com.fiap.thermoscope.repositories;

import br.com.fiap.thermoscope.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TeamRepository extends JpaRepository<Team, UUID> {
}
