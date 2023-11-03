package br.com.lbenaducci.inquisition.domain.match;

import br.com.lbenaducci.inquisition.domain.character.base.AbstractCharacter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Voting {
	private final Map<AbstractCharacter, AbstractCharacter> voting = new HashMap<>();

	public void vote(AbstractCharacter voter, AbstractCharacter target) {
		if(voter.equals(target)) {
			throw new IllegalArgumentException("Character cannot vote yourself");
		}
		if(!voting.containsKey(voter)) {
			voting.put(voter, target);
		} else {
			throw new IllegalArgumentException("Character cannot vote more than once");
		}
	}

	public AbstractCharacter getWinner() {
		List<AbstractCharacter> votes = new ArrayList<>();
		voting.forEach((k, v) -> {
			if(k.isAlive()) {
				votes.add(v);
			}
		});
		Map<AbstractCharacter, Long> counting = votes.stream().filter(AbstractCharacter::isAlive).collect(Collectors.groupingBy(e -> e, Collectors.counting()));
		return counting.entrySet().stream()
		               .max(Map.Entry.comparingByValue())
		               .map(Map.Entry::getKey)
		               .orElse(null);
	}
}
