package br.com.lbenaducci.inquisition.domain.match.stage;

import br.com.lbenaducci.inquisition.domain.character.night.GroupNightAction;
import br.com.lbenaducci.inquisition.domain.match.Voting;

import java.util.function.BiConsumer;

public abstract non-sealed class VotingStage<R> extends Stage<R> {
	protected final Voting voting = new Voting();

	public final <O extends GroupNightAction> void action(O character, BiConsumer<O, Voting> action) {
		if(!character.isAlive()) {
			throw new IllegalArgumentException("Character is not alive");
		}
		if(character.equals(nextCharacter())){
			action.accept(character, voting);
			removeQueue(character);
		}else {
			throw new IllegalArgumentException("Character is not a next character");
		}
	}

}
