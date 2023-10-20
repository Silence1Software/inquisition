package br.com.lbenaducci.inquisition.domain.match.stage;

import br.com.lbenaducci.inquisition.domain.character.Character;
import br.com.lbenaducci.inquisition.domain.character.NightCharacter;
import br.com.lbenaducci.inquisition.domain.match.MatchPlayer;
import br.com.lbenaducci.inquisition.domain.match.stage.dtos.NightResult;
import br.com.lbenaducci.inquisition.domain.match.stage.dtos.NightTurnCharacter;

public final class NightStage extends InitialStage<NightResult, NightTurnCharacter> {
	@Override
	protected EventStage nextEvent() {
		return new EventStage();
	}

	@Override
	protected NightTurnCharacter toTurnCharacter(MatchPlayer matchPlayer) {
		return new NightTurnCharacter(matchPlayer, matchPlayer.getCharacter() instanceof NightCharacter);
	}

	@Override
	public NightResult getResult() {
		return null;
	}
}
