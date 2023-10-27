package br.com.lbenaducci.inquisition.domain.match.stage;

import br.com.lbenaducci.inquisition.domain.character.base.Character;

import java.util.Set;

public final class EndStage extends Stage<Set<Character>> {
	@Override
	protected Stage<?> nextEvent() {
		return null;
	}

	@Override
	protected boolean canDoAction(Character character) {
		return false;
	}

	@Override
	public Set<Character> getResult() {
		return getCharacters();
	}
}
