package br.com.lbenaducci.inquisition.domain.character;

import java.util.Set;

public interface Character {
	String getName();

	String getDescription();

	CharacterStatus getStatus();

	void setStatus(CharacterStatus status);

	boolean canVote();

	void setCanVote(boolean canVote);

	Character onKicked();

	boolean win(Set<Character> characters);
}
