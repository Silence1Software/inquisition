package br.com.lbenaducci.inquisition.domain.match.stage.dtos;

import br.com.lbenaducci.inquisition.domain.character.Character;

public class NightTurnCharacter extends TurnCharacter {
	public NightTurnCharacter(Character character, boolean canDoAction) {
		super(character, canDoAction);
	}
}
