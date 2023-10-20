package br.com.lbenaducci.inquisition.domain.match.stage.dtos;

import br.com.lbenaducci.inquisition.domain.match.MatchPlayer;

public abstract class TurnCharacter {
	private final MatchPlayer matchPlayer;
	private boolean canDoAction;

	protected TurnCharacter(MatchPlayer matchPlayer, boolean canDoAction) {
		this.matchPlayer = matchPlayer;
		this.canDoAction = canDoAction;
	}

	public MatchPlayer getMatchPlayer() {
		return matchPlayer;
	}

	public boolean canDoAction() {
		return canDoAction;
	}

	public void setCanDoAction(boolean canDoAction) {
		this.canDoAction = canDoAction;
	}
}
