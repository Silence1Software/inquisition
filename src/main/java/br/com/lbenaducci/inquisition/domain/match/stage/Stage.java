package br.com.lbenaducci.inquisition.domain.match.stage;

import br.com.lbenaducci.inquisition.domain.character.base.Character;

import java.util.*;
import java.util.stream.Collectors;

public abstract sealed class Stage<R> permits DiscussionStage, EndStage, EventStage, InitialStage, VotingStage {
	private final List<Character> characters = new ArrayList<>();

	public final Stage<?> next() {
		Set<Character> characterSet = getCharacters();
		Set<Character> winners = characterSet.stream()
		                                     .filter(it -> it.isWinner(characterSet))
		                                     .collect(Collectors.toSet());
		if(winners.isEmpty()) {
			Stage<?> nextEvent = nextEvent();
			nextEvent.setCharacters(characterSet);
			return nextEvent;
		}

		Stage<?> end = new EndStage();
		end.setCharacters(winners);

		return end;
	}

	public final Set<Character> getCharacters() {
		return characters.stream().collect(Collectors.toUnmodifiableSet());
	}

	protected final void setCharacters(Set<Character> matchPlayers) {
		this.characters.addAll(matchPlayers);
		Collections.shuffle(characters);
	}

	public final SequencedSet<Character> getSequenceAction() {
		return characters.stream()
		                 .filter(this::canDoAction)
		                 .collect(Collectors.toCollection(LinkedHashSet::new));
	}

	protected abstract Stage<?> nextEvent();

	protected abstract boolean canDoAction(Character character);

	public abstract R getResult();
}
