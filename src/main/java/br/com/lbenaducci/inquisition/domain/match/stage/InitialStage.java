package br.com.lbenaducci.inquisition.domain.match.stage;

import br.com.lbenaducci.inquisition.domain.match.MatchPlayer;
import br.com.lbenaducci.inquisition.domain.match.stage.dtos.TurnCharacter;

import java.util.Set;

public abstract sealed class InitialStage<R, T extends TurnCharacter> extends Stage<R, T> permits DayStage, NightStage {

	public static void start(InitialStage<?, ?> startEvent, Set<MatchPlayer> matchPlayer) {
		startEvent.setMatchPlayers(matchPlayer);
	}
}
