package br.com.lbenaducci.inquisition.domain.character;

import java.util.Set;

public class Villager implements Character {
	private CharacterStatus status = CharacterStatus.ALIVE;
	private boolean canVote = true;

	@Override
	public String getName() {
		return "Villager";
	}

	@Override
	public String getDescription() {
		return "Do Nothing";
	}

	@Override
	public CharacterStatus getStatus() {
		return status;
	}

	@Override
	public void setStatus(CharacterStatus status) {
		this.status = status;
	}

	@Override
	public boolean canVote() {
		return canVote;
	}

	@Override
	public void setCanVote(boolean canVote) {
		this.canVote = canVote;
	}

	@Override
	public Character onKicked() {
		setStatus(CharacterStatus.DEAD);
		return this;
	}

	@Override
	public boolean win(Set<Character> characters) {
		return status != CharacterStatus.DEAD && characters.stream().allMatch(Villager.class::isInstance);
	}
}
