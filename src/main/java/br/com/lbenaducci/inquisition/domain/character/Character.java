package br.com.lbenaducci.inquisition.domain.character;

import br.com.lbenaducci.inquisition.domain.match.Voting;
import br.com.lbenaducci.inquisition.domain.player.Player;

import java.util.Set;

public non-sealed abstract class Character extends CharacterInfo {

	public final void vote(Character target, Voting voting) {
		if(canVote()) {
			voting.vote(this, target);
		}
	}

	public abstract boolean isWinner(Set<Character> characters);

	public void onKicked() {
		setStatus(CharacterStatus.DEAD);
	}

	public void onDeath() {
		if(getStatus() != CharacterStatus.PROTECTED) {
			setStatus(CharacterStatus.ALIVE);
		} else {
			setStatus(CharacterStatus.DEAD);
		}
	}
}
