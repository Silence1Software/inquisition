package br.com.lbenaducci.inquisition.domain.character;

import java.util.Set;

public abstract class Character {
	private CharacterStatus status;
	private boolean canVote;

	public abstract String getName();

	public abstract String getDescription();

	public final CharacterStatus getStatus() {
		return status;
	}

	public final boolean isAlive() {
		return getStatus() != CharacterStatus.DEAD;
	}

	public final void protect() {
		if(isAlive()) {
			this.status = CharacterStatus.PROTECTED;
		}
	}

	public final boolean canVote() {
		return canVote && isAlive();
	}

	public final void setCanVote(boolean canVote) {
		this.canVote = canVote;
	}

	public abstract boolean isWinner(Set<Character> characters);

	public void onKicked() {
		this.status = CharacterStatus.DEAD;
	}

	public void onDeath() {
		if(this.status != CharacterStatus.PROTECTED) {
			this.status = CharacterStatus.ALIVE;
		} else {
			this.status = CharacterStatus.DEAD;
		}
	}
}
