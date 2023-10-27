package br.com.lbenaducci.inquisition.domain.character;

import br.com.lbenaducci.inquisition.domain.match.Voting;
import br.com.lbenaducci.inquisition.domain.player.Player;

import java.util.Set;

public abstract class Character {
	private Player player;
	private CharacterStatus status;
	private boolean canVote;

	public final Player getPlayer() {
		return player;
	}

	public final void setPlayer(Player player) {
		if(this.player != null) {
			this.player = player;
		}
	}

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

	public final void vote(Character target, Voting voting) {
		if(canVote()) {
			voting.vote(this, target);
		}
	}

	public abstract boolean isWinner(Set<Character> characters);

	public abstract String getName();

	public abstract String getDescription();

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
