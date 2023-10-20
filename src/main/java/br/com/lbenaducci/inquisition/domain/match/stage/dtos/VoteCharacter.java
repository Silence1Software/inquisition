package br.com.lbenaducci.inquisition.domain.match.stage.dtos;

import br.com.lbenaducci.inquisition.domain.character.Character;

public class VoteCharacter extends TurnCharacter{
	private int votes;

	public VoteCharacter(Character character, boolean canVote) {
		super(character, canVote);
		this.votes = 0;
	}

	public int getVotes() {
		return votes;
	}

	public void vote() {
		this.votes++;
	}
}
