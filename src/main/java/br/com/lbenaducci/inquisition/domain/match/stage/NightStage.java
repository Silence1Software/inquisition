package br.com.lbenaducci.inquisition.domain.match.stage;

import br.com.lbenaducci.inquisition.domain.character.Character;

public final class NightStage extends InitialStage<Void> {

	@Override
	protected Stage<?> nextEvent() {
		return null;
	}

	@Override
	protected boolean canDoAction(Character character) {
		return false;
	}

	@Override
	public Void getResult() {
		return null;
	}
}
