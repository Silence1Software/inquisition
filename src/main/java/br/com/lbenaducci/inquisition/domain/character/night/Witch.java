package br.com.lbenaducci.inquisition.domain.character.night;

import br.com.lbenaducci.inquisition.domain.character.base.AbstractCharacter;
import br.com.lbenaducci.inquisition.domain.match.Voting;

import java.util.Set;

public class Witch extends AbstractCharacter implements GroupNightAction {

	@Override
	public String getName() {
		return "Witch";
	}

	@Override
	public String getDescription() {
		return "Kill Villagers";
	}

	@Override
	public boolean isWinner(Set<AbstractCharacter> characters) {
		return isAlive() && characters.stream().allMatch(Witch.class::isInstance);
	}

	@Override
	public void nightVote(Voting voting, AbstractCharacter target) {
		if(target instanceof Witch) {
			throw new IllegalArgumentException("Target must not be a Witch");
		}
		if(!target.isAlive()) {
			throw new IllegalArgumentException("Target must be alive");
		}
		voting.vote(this, target);
	}
}
