package br.com.lbenaducci.inquisition.domain.match.stage.dtos;

import br.com.lbenaducci.inquisition.domain.character.Character;

public abstract class TurnCharacter {
	private final Character character;
	private boolean canDoAction;

	public TurnCharacter(Character character, boolean canDoAction) {
		this.character = character;
		this.canDoAction = canDoAction;
	}

	public Character getCharacter() {
		return character;
	}

	public boolean canDoAction() {
		return canDoAction;
	}

	public void setCanDoAction(boolean canDoAction) {
		this.canDoAction = canDoAction;
	}
}
