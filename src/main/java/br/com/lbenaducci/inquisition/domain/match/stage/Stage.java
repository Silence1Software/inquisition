package br.com.lbenaducci.inquisition.domain.match.stage;

import br.com.lbenaducci.inquisition.domain.character.base.AbstractCharacter;
import br.com.lbenaducci.inquisition.domain.character.base.Character;

import java.util.*;
import java.util.stream.Collectors;

public abstract sealed class Stage<R> permits DiscussionStage, EndStage, EventStage, JudgmentStage, VotingStage {
	private final List<AbstractCharacter> characters = new ArrayList<>();
	private final List<Character> queue = new ArrayList<>();

	public final Stage<?> nextStage() {
		Set<AbstractCharacter> characterSet = getCharacters();
		Set<AbstractCharacter> winners = characterSet.stream()
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

	public final Set<AbstractCharacter> getCharacters() {
		return characters.stream().collect(Collectors.toUnmodifiableSet());
	}

	protected final void setCharacters(Set<AbstractCharacter> characters) {
		this.characters.addAll(characters);
		Collections.shuffle(this.characters);
		queue.addAll(getSequenceAction());
	}

	protected void removeQueue(Character character) {
		queue.remove(character);
	}

	public Character nextCharacter() {
		return queue.getFirst();
	}

	public final SequencedSet<AbstractCharacter> getSequenceAction() {
		return characters.stream()
		                 .filter(this::canDoAction)
		                 .collect(Collectors.toCollection(LinkedHashSet::new));
	}

	protected abstract Stage<?> nextEvent();

	protected abstract boolean canDoAction(AbstractCharacter character);

	protected abstract R getResult();

	public R result() {
		if(queue.isEmpty()) {
			return getResult();
		}
		throw new IllegalStateException("Queue is not empty");
	}
}
