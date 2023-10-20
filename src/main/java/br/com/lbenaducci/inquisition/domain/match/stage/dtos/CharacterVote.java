package br.com.lbenaducci.inquisition.domain.match.stage.dtos;

import br.com.lbenaducci.inquisition.domain.character.Character;

public class CharacterVote {
	private final Character character;
	private boolean canVote;
	private int votes;

	public CharacterVote(Character character, boolean canVote) {
		this.character = character;
		this.canVote = canVote;
		this.votes = 0;
	}

	public Character getCharacter() {
		return character;
	}

	public boolean canVote() {
		return canVote;
	}

	public int getVotes() {
		return votes;
	}

	public void setCanVote(boolean canVote) {
		this.canVote = canVote;
	}

	public void vote() {
		this.votes++;
	}
}
