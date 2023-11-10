package br.com.lbenaducci.inquisition.domain.player;

import java.util.Optional;
import java.util.UUID;

public interface PlayerRepository {
	Player save(Player player);

	Optional<Player> findById(UUID id);

	void deleteById(UUID id);
}
