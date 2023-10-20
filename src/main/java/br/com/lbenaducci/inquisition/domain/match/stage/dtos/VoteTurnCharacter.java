package br.com.lbenaducci.inquisition.domain.match.stage.dtos;

import br.com.lbenaducci.inquisition.domain.character.Character;

public class VoteTurnCharacter extends TurnCharacter{
	private int votes;

	public VoteTurnCharacter(Character character, boolean canVote) {
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
