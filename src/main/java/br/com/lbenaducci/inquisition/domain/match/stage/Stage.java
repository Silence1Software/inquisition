package br.com.lbenaducci.inquisition.domain.match.stage;

import br.com.lbenaducci.inquisition.domain.character.Character;
import br.com.lbenaducci.inquisition.domain.match.MatchPlayer;
import br.com.lbenaducci.inquisition.domain.match.stage.dtos.TurnCharacter;

import java.util.*;

import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toSet;

public abstract sealed class Stage<R, T extends TurnCharacter> permits DiscussionStage, EndStage, EventStage, InitialStage, VotingStage {
	private final Set<MatchPlayer> matchPlayers = new HashSet<>();
	protected final List<T> turnCharacters = new ArrayList<>();

	public final Stage<?, ? extends TurnCharacter> next() {
		Set<Character> characters = getMatchPlayer().stream().map(MatchPlayer::getCharacter).collect(toSet());
		Set<MatchPlayer> winners = getMatchPlayer().stream()
		                                           .filter(it -> it.getCharacter().isWinner(characters))
		                                           .collect(toSet());
		if(winners.isEmpty()) {
			Stage<?, ?> nextEvent = nextEvent();
			nextEvent.setMatchPlayers(matchPlayers);
			return nextEvent;
		}

		Stage<?, ?> end = new EndStage();
		end.setMatchPlayers(winners);

		return end;
	}

	protected abstract Stage<?, ? extends TurnCharacter> nextEvent();

	public final Set<MatchPlayer> getMatchPlayer() {
		return Collections.unmodifiableSet(matchPlayers);
	}

	protected void setMatchPlayers(Set<MatchPlayer> matchPlayers) {
		this.matchPlayers.addAll(matchPlayers);
		getMatchPlayer().forEach(it -> turnCharacters.add(toTurnCharacter(it)));
		Collections.shuffle(turnCharacters);
	}

	protected abstract T toTurnCharacter(MatchPlayer matchPlayers);

	public SequencedSet<MatchPlayer> getSequenceAction() {
		return turnCharacters.stream()
		                     .filter(TurnCharacter::canDoAction)
		                     .map(TurnCharacter::getMatchPlayer)
		                     .collect(toCollection(LinkedHashSet::new));
	}

	public abstract R getResult();
}
