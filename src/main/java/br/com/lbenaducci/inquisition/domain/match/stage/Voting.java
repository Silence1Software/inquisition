package br.com.lbenaducci.inquisition.domain.match.stage;

import br.com.lbenaducci.inquisition.domain.character.Character;
import br.com.lbenaducci.inquisition.domain.character.CharacterStatus;
import br.com.lbenaducci.inquisition.domain.match.stage.dtos.CharacterVote;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toSet;

public final class Voting implements Stage {
	private final List<CharacterVote> votes = new ArrayList<>();

	@Override
	public Stage next() {
		Set<Character> winners = votes.stream()
		                              .map(CharacterVote::getCharacter)
		                              .collect(collectingAndThen(toSet(), l -> l.stream()
		                                                                        .filter(it -> it.win(l))
		                                                                        .collect(toSet())));
		if(winners.isEmpty()) {
			return new Day();
		}

		End end = new End();
		end.setCharacters(winners);

		return end;
	}

	@Override
	public SequencedSet<Character> getCharacter() {
		return votes.stream()
		            .filter(CharacterVote::canVote)
		            .map(CharacterVote::getCharacter)
		            .collect(Collectors.toCollection(LinkedHashSet::new));
	}

	@Override
	public void setCharacters(Set<Character> characters) {
		characters.forEach(it -> {
			votes.add(new CharacterVote(it, it.getStatus() != CharacterStatus.DEAD && it.canVote()));
		});
		Collections.shuffle(votes);
	}

	public void vote(Character voter, Character target) {
		AtomicReference<CharacterVote> atmVoter = new AtomicReference<>();
		AtomicReference<CharacterVote> atmTarget = new AtomicReference<>();
		votes.stream()
		     .filter(it -> it.getCharacter().equals(voter) || it.getCharacter().equals(target))
		     .forEach(it -> {
			     if(it.getCharacter().equals(voter)) {
				     atmVoter.set(it);
			     } else {
				     atmTarget.set(it);
			     }
		     });
		CharacterVote characterVoter = atmVoter.get();
		CharacterVote characterTarget = atmTarget.get();
		if(characterVoter == null || !characterVoter.canVote()) {
			throw new IllegalArgumentException("Character cannot vote");
		}
		if(characterTarget == null || characterTarget.getCharacter().getStatus() == CharacterStatus.DEAD) {
			throw new IllegalArgumentException("Character cannot voted");
		}
		characterTarget.vote();
		characterVoter.setCanVote(false);
	}

	public Character getResult() {
		if(votes.stream().anyMatch(CharacterVote::canVote)) {
			throw new IllegalArgumentException("All characters must vote");
		}
		Integer max = votes.stream()
		                   .map(CharacterVote::getVotes)
		                   .max(Integer::compareTo)
		                   .orElse(null);
		List<Character> mostVoted = votes.stream()
		                                 .filter(it -> it.getVotes() == max)
		                                 .map(CharacterVote::getCharacter)
		                                 .toList();
		if(mostVoted.size() > 1) {
			return null;
		}
		return mostVoted.getFirst().onKicked();
	}
}
