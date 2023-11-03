package br.com.lbenaducci.inquisition.domain.match.stage;

import br.com.lbenaducci.inquisition.domain.character.base.AbstractCharacter;

import java.util.Set;

public sealed interface InitialStage permits DayStage, NightStage {

	static <S extends Stage<?> & InitialStage> void start(S startEvent, Set<AbstractCharacter> characters) {
		startEvent.setCharacters(characters);
	}
}
