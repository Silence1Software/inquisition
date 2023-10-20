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
		if(status == CharacterStatus.DEAD) {
			throw new IllegalArgumentException("Cannot set status to DEAD");
		}
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
	public boolean isWinner(Set<Character> characters) {
		return status != CharacterStatus.DEAD && characters.stream().allMatch(Villager.class::isInstance);
	}

	@Override
	public void onKicked() {
		this.status = CharacterStatus.DEAD;
	}

	@Override
	public void onDeath() {
		this.status = CharacterStatus.DEAD;
	}
}
