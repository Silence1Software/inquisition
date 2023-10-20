package br.com.lbenaducci.inquisition.domain.match.stage;

import br.com.lbenaducci.inquisition.domain.character.Character;
import br.com.lbenaducci.inquisition.domain.match.stage.dtos.TurnCharacter;

public final class Day extends Stage<Void, TurnCharacter> implements InitialStage{

	@Override
	protected Stage<?, ? extends TurnCharacter> nextEvent() {
		return null;
	}

	@Override
	protected TurnCharacter toTurnCharacter(Character character) {
		return null;
	}

	@Override
	public Void getResult() {
		return null;
	}
}
