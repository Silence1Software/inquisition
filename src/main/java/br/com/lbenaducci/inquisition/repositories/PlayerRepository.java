package br.com.lbenaducci.inquisition.repositories;

import br.com.lbenaducci.inquisition.models.entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PlayerRepository extends JpaRepository<Player, UUID> {
}