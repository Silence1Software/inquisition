package br.com.lbenaducci.inquisition.domain.generic;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class GenericEntity {
	private final UUID id;
	private final LocalDateTime createdAt;

	protected GenericEntity(UUID id) {
		if(id == null) {
			id = UUID.randomUUID();
		}
		this.id = id;
		this.createdAt = LocalDateTime.now();
	}

	protected GenericEntity() {
		this(null);
	}

	public abstract void validate();

	public UUID getId() {
		return id;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
}
