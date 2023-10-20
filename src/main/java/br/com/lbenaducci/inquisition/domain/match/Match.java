package br.com.lbenaducci.inquisition.domain.match;

import br.com.lbenaducci.inquisition.domain.generic.GenericEntity;
import br.com.lbenaducci.inquisition.domain.match.stage.InitialStage;
import br.com.lbenaducci.inquisition.domain.match.stage.Stage;
import br.com.lbenaducci.inquisition.domain.player.Player;

import java.util.Collections;
import java.util.SequencedSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Match extends GenericEntity {
	private final Set<MatchPlayer> matchPlayers;
	private MatchStatus status;
	private Stage<?, ?> currentStage;

	public Match(Set<Player> players, InitialStage<?, ?> initialStage) {
		this.matchPlayers = CharacterRandomizer.randomize(players);
		status = MatchStatus.RUNNING;
		currentStage = initialStage;
		InitialStage.start(initialStage, matchPlayers);
		validate();
	}

	@Override
	public void validate() {
		if(matchPlayers == null) {
			throw new IllegalArgumentException("Characters cannot be null");
		}
		if(status == null) {
			throw new IllegalArgumentException("Status cannot be null");
		}
		if(matchPlayers.size() < 5) {
			throw new IllegalArgumentException("Must have at least 5 characters");
		}
		if(currentStage == null) {
			throw new IllegalArgumentException("Current stage cannot be null");
		}
	}

	public SequencedSet<MatchPlayer> getSequenceAction() {
		return currentStage.getSequenceAction();
	}

	public Set<MatchPlayer> getMatchPlayers() {
		return Collections.unmodifiableSet(matchPlayers);
	}

	public MatchStatus getStatus() {
		return status;
	}

	public void setStatus(MatchStatus status) {
		this.status = status;
	}
}
