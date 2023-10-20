package br.com.lbenaducci.inquisition.domain.match.stage;

import br.com.lbenaducci.inquisition.domain.character.Character;
import br.com.lbenaducci.inquisition.domain.match.stage.dtos.TurnCharacter;

import java.util.*;

import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toSet;

public abstract sealed class Stage<R, T extends TurnCharacter> permits Day, Discussion, End, Event, Night, Voting {
	private final Set<Character> characters = new HashSet<>();
	protected final List<T> turnCharacters = new ArrayList<>();

	public static <O extends Stage<?, ? extends TurnCharacter> & InitialStage> void start(O startEvent, Set<Character> characters) {
		((Stage<?, ?>) startEvent).setCharacters(characters);
	}

	public final Stage<?, ? extends TurnCharacter> next() {
		Set<Character> winners = getCharacter().stream()
		                                       .filter(it -> it.isWinner(getCharacter()))
		                                       .collect(toSet());
		if(winners.isEmpty()) {
			Stage<?, ?> nextEvent = nextEvent();
			nextEvent.setCharacters(characters);
			return nextEvent;
		}

		Stage<?, ?> end = new End();
		end.setCharacters(winners);

		return end;
	}

	protected abstract Stage<?, ? extends TurnCharacter> nextEvent();

	public final Set<Character> getCharacter() {
		return Collections.unmodifiableSet(characters);
	}

	private void setCharacters(Set<Character> characters) {
		this.characters.addAll(characters);
		getCharacter().forEach(it -> turnCharacters.add(toTurnCharacter(it)));
		Collections.shuffle(turnCharacters);
	}

	protected abstract T toTurnCharacter(Character character);

	public SequencedSet<Character> getSequenceAction() {
		return turnCharacters.stream()
		                     .filter(TurnCharacter::canDoAction)
		                     .map(TurnCharacter::getCharacter)
		                     .collect(toCollection(LinkedHashSet::new));
	}

	public abstract R getResult();
}
