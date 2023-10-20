package br.com.lbenaducci.inquisition.domain.match.stage.dtos;

import br.com.lbenaducci.inquisition.domain.match.MatchPlayer;

public class NightTurnCharacter extends TurnCharacter {
	public NightTurnCharacter(MatchPlayer matchPlayer, boolean canDoAction) {
		super(matchPlayer, canDoAction);
	}
}
