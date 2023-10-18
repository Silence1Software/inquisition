package br.com.lbenaducci.inquisition.domain.match.stage;

import br.com.lbenaducci.inquisition.domain.character.Character;

import java.util.SequencedSet;
import java.util.Set;

public final class End implements Stage{
	@Override
	public Stage next() {
		return null;
	}

	@Override
	public SequencedSet<Character> getCharacter() {
		return null;
	}

	@Override
	public void setCharacters(Set<Character> characters) {

	}
}
