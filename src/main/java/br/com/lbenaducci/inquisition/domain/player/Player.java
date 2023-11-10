package br.com.lbenaducci.inquisition.domain.player;

import br.com.lbenaducci.inquisition.domain.exception.ValidationException;

import java.time.LocalDateTime;
import java.util.UUID;

public class Player {
	private final UUID id;
	private final LocalDateTime createdAt;
	private String name;

	public Player(UUID id, LocalDateTime createdAt, String name) {
		this.id = id;
		this.createdAt = createdAt;
		this.name = name;
		validate();
	}

	public UUID getId() {
		return id;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public String getName() {
		return name;
	}

	public void changeName(String name) {
		this.name = name;
		validate();
	}

	private void validate() {
		if(name == null || name.isBlank() || name.length() > 255 || name.length() < 3) {
			throw new ValidationException("Name must be between 3 and 256 characters");
		}
	}
}
