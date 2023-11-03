package br.com.lbenaducci.inquisition.domain.character.base;

import java.util.Set;

public interface Character {
	boolean isWinner(Set<AbstractCharacter> characters);

	boolean isAlive();
}
