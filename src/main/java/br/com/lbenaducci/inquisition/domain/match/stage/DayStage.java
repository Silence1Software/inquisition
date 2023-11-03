package br.com.lbenaducci.inquisition.domain.match.stage;

import br.com.lbenaducci.inquisition.domain.character.base.AbstractCharacter;

public final class DayStage extends VotingStage<Void> implements InitialStage {
	@Override
	protected Stage<?> nextEvent() {
		return null;
	}

	@Override
	protected boolean canDoAction(AbstractCharacter character) {
		return false;
	}

	@Override
	public Void getResult() {
		return null;
	}

}
