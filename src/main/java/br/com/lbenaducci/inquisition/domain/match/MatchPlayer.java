package br.com.lbenaducci.inquisition.domain.match;

import br.com.lbenaducci.inquisition.domain.character.Character;
import br.com.lbenaducci.inquisition.domain.generic.GenericEntity;
import br.com.lbenaducci.inquisition.domain.player.Player;

public class MatchPlayer extends GenericEntity {
	private final Player player;
	private final Character character;

	public MatchPlayer(Player player, Character character) {
		this.player = player;
		this.character = character;
		validate();
	}

	@Override
	public void validate() {
		if(player == null) {
			throw new IllegalArgumentException("Player cannot be null");
		}
		if(character == null) {
			throw new IllegalArgumentException("Character cannot be null");
		}
	}

	public Player getPlayer() {
		return player;
	}

	public Character getCharacter() {
		return character;
	}

	@Override
	public String toString() {
		return "MatchPlayer{" +
				"player=" + player.getName() +
				", character=" + character.getName() +
				'}';
	}
}
