package br.com.lbenaducci.inquisition.domain.match.stage;

import br.com.lbenaducci.inquisition.domain.character.Character;

import java.util.SequencedSet;
import java.util.Set;

public sealed interface Stage<T> permits Day, Discussion, End, Event, Night, Voting {
	Stage<?> next();
	SequencedSet<Character> getCharacter();
	void setCharacters(Set<Character> characters);

	T getResult();
}
