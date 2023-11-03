package br.com.lbenaducci.inquisition.domain.match.stage;

import br.com.lbenaducci.inquisition.domain.character.base.AbstractCharacter;
import br.com.lbenaducci.inquisition.domain.character.base.Character;
import br.com.lbenaducci.inquisition.domain.match.Voting;

import java.util.function.Function;

public final class VotingStage extends Stage<AbstractCharacter> {
	private final Voting voting = new Voting();

	@Override
	protected DayStage nextEvent() {
		return new DayStage();
	}

	@Override
	protected boolean canDoAction(AbstractCharacter character) {
		return character.canVote();
	}

	@Override
	public AbstractCharacter getResult() {
		AbstractCharacter kicked = voting.getWinner();
		kicked.onKicked();
		return kicked;
	}

	public Voting getVoting() {
		return voting;
	}
}
