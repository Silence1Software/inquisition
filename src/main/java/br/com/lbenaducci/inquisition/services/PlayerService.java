package br.com.lbenaducci.inquisition.services;

import br.com.lbenaducci.inquisition.exception.NotFoundException;
import br.com.lbenaducci.inquisition.exception.ValidationException;
import br.com.lbenaducci.inquisition.models.entities.Player;
import br.com.lbenaducci.inquisition.repositories.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PlayerService {
	private final PlayerRepository playerRepository;

	public PlayerService(PlayerRepository playerRepository) {
		this.playerRepository = playerRepository;
	}

	public Player save(Player player) {
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
		player.setName(name);
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
