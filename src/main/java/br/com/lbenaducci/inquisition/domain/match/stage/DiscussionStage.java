package br.com.lbenaducci.inquisition.domain.match.stage;

import br.com.lbenaducci.inquisition.domain.character.base.AbstractCharacter;

public final class DiscussionStage extends Stage<Void> {
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
