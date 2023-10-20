package br.com.lbenaducci.inquisition.domain.match.stage;

import br.com.lbenaducci.inquisition.domain.character.Character;
import br.com.lbenaducci.inquisition.domain.match.stage.dtos.NightTurnCharacter;
import br.com.lbenaducci.inquisition.domain.match.stage.dtos.NightResult;

public final class Night extends Stage<NightResult, NightTurnCharacter> {

	@Override
	protected Event nextEvent() {
		return new Event();
	}

	@Override
	protected NightTurnCharacter toTurnCharacter(Character character) {
		return new NightTurnCharacter(character, character instanceof br.com.lbenaducci.inquisition.domain.character.NightCharacter);
	}

	@Override
	public NightResult getResult() {
		return null;
	}
}
