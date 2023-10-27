package br.com.lbenaducci.inquisition.domain.match.stage;

import br.com.lbenaducci.inquisition.domain.character.base.Character;
import br.com.lbenaducci.inquisition.domain.character.night.NightCharacter;

public final class NightStage extends InitialStage<Void> {

	@Override
	protected Stage<?> nextEvent() {
		return new DiscussionStage();
	}

	@Override
	protected boolean canDoAction(Character character) {
		return character instanceof NightCharacter;
	}

	@Override
	public Void getResult() {
		return null;
	}
}
