package br.com.lbenaducci.inquisition.domain.match.stage;

import br.com.lbenaducci.inquisition.domain.character.Character;
import br.com.lbenaducci.inquisition.domain.character.CharacterStatus;

import java.util.*;
import java.util.stream.Collectors;

public final class Voting implements Stage {
	private final Map<Character, Integer> votes = new HashMap<>();
	private final Set<Character> characters = new HashSet<>();
	private final SequencedMap<Character, Boolean> available = new LinkedHashMap<>();

	@Override
	public Stage next() {
		Set<Character> winners = characters.stream().filter(it -> it.win(characters)).collect(Collectors.toSet());
		if(winners.isEmpty()){
			return new Day();
		}
		End end = new End();
		end.setCharacters(winners);
		return end;
	}

	@Override
	public SequencedSet<Character> getCharacter() {
		return available.sequencedKeySet();
	}

	@Override
	public void setCharacters(Set<Character> characters) {
		this.characters.addAll(characters);
		characters.stream()
		          .filter(it -> it.getStatus() != CharacterStatus.DEAD || it.canVote())
		          .collect(Collectors.collectingAndThen(Collectors.toList(), l -> {
			          Collections.shuffle(l);
			          return l;
		          })).forEach(it -> available.put(it, true));
	}

	public void vote(Character voter, Character target) {
		boolean permits = available.getOrDefault(voter, false);
		if(!permits) {
			throw new IllegalArgumentException("Character cannot vote");
		}
		if(target.getStatus() == CharacterStatus.DEAD) {
			throw new IllegalArgumentException("Character cannot voted");
		}
		votes.put(target, votes.get(target) + 1);
		available.put(voter, false);
	}

	public Character getResult() {
		if(available.containsValue(true)) {
			throw new IllegalArgumentException("All characters must vote");
		}
		Integer max = votes.values()
		                   .stream()
		                   .max(Integer::compareTo)
		                   .orElse(null);
		List<Character> mostVoted = votes.entrySet()
		                                 .stream()
		                                 .filter(it -> it.getValue().equals(max))
		                                 .map(Map.Entry::getKey)
		                                 .toList();
		if(mostVoted.size() > 1) {
			return null;
		}
		return mostVoted.getFirst().onKicked();
	}
}
