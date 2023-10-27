package br.com.lbenaducci.inquisition.domain.match.stage;

import br.com.lbenaducci.inquisition.domain.character.base.Character;

import java.util.Set;

public abstract sealed class InitialStage<R> extends Stage<R> permits DayStage, NightStage {

	public static void start(InitialStage<?> startEvent, Set<Character> characters) {
		startEvent.setCharacters(characters);
	}
}
