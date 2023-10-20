package br.com.lbenaducci.inquisition.domain.match.stage.dtos;

import br.com.lbenaducci.inquisition.domain.match.MatchPlayer;

public class VoteTurnCharacter extends TurnCharacter {
	private int votes;

	public VoteTurnCharacter(MatchPlayer matchPlayer, boolean canVote) {
		super(matchPlayer, canVote);
		this.votes = 0;
	}

	public int getVotes() {
		return votes;
	}

	public void vote() {
		this.votes++;
	}
}
