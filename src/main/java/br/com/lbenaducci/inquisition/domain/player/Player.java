package br.com.lbenaducci.inquisition.domain.player;

import br.com.lbenaducci.inquisition.domain.generic.GenericEntity;

import java.util.UUID;

public class Player extends GenericEntity {
	private String name;

	public Player(String name) {
		this(null, name);
	}

	public Player(UUID id, String name) {
		super(id);
		this.name = name;
		validate();
	}

	@Override
	public void validate() {
		if(name == null || name.isBlank()) {
			throw new IllegalArgumentException("Name cannot be null or blank");
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
