package br.com.lbenaducci.inquisition.domain.match.stage;

import br.com.lbenaducci.inquisition.domain.character.Character;
import br.com.lbenaducci.inquisition.domain.match.Voting;

public final class VotingStage extends Stage<Character> {
	private final Voting voting = new Voting();

	@Override
	protected DayStage nextEvent() {
		return new DayStage();
	}

	@Override
	protected boolean canDoAction(Character character) {
		return character.canVote();
	}

	@Override
	public Character getResult() {
		Character kicked = voting.getWinner();
		kicked.onKicked();
		return kicked;
	}

	public Voting getVoting() {
		return voting;
	}
}
