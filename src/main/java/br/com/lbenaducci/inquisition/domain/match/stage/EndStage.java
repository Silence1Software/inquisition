package br.com.lbenaducci.inquisition.domain.match.stage;

import br.com.lbenaducci.inquisition.domain.character.base.AbstractCharacter;

import java.util.Set;

public final class EndStage extends Stage<Set<AbstractCharacter>> {
	@Override
	protected Stage<?> nextEvent() {
		return null;
	}

	@Override
	protected boolean canDoAction(AbstractCharacter character) {
		return false;
	}

	@Override
	public Set<AbstractCharacter> getResult() {
		return getCharacters();
	}
}
