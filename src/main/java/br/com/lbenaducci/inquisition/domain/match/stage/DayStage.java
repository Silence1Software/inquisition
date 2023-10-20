package br.com.lbenaducci.inquisition.domain.match.stage;

import br.com.lbenaducci.inquisition.domain.match.MatchPlayer;
import br.com.lbenaducci.inquisition.domain.match.stage.dtos.TurnCharacter;

public final class DayStage extends InitialStage<Void, TurnCharacter> {

	@Override
	protected Stage<?, ? extends TurnCharacter> nextEvent() {
		return null;
	}

	@Override
	protected TurnCharacter toTurnCharacter(MatchPlayer matchPlayers) {
		return null;
	}

	@Override
	public Void getResult() {
		return null;
	}
}
