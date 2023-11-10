package br.com.lbenaducci.inquisition.domain.player;

import br.com.lbenaducci.inquisition.domain.exception.NotFoundException;
import br.com.lbenaducci.inquisition.domain.exception.ValidationException;

import java.time.LocalDateTime;
import java.util.UUID;

public class PlayerUseCase {
	private final PlayerRepository playerRepository;

	public PlayerUseCase(PlayerRepository playerRepository) {
		this.playerRepository = playerRepository;
	}

	public Player create(String name) {
		Player player = new Player(UUID.randomUUID(), LocalDateTime.now(), name);
		return playerRepository.save(player);
	}

	public Player find(UUID id) {
		if(id == null) {
			throw new ValidationException("Id must not be null");
		}
		return playerRepository.findById(id).orElseThrow(() -> new NotFoundException("Player '" + id + "' not found"));
	}

	public Player changeName(UUID id, String name) {
		if(name == null) {
			throw new ValidationException("Name must not be null");
		}
		Player player = find(id);
		player.changeName(name);
		playerRepository.save(player);
		return player;
	}

	public void delete(UUID id) {
		if(id == null) {
			throw new ValidationException("Id must not be null");
		}
		playerRepository.deleteById(id);
	}
}
