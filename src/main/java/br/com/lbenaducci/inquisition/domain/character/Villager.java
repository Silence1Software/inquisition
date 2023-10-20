package br.com.lbenaducci.inquisition.domain.character;

import java.util.Set;

public class Villager extends Character {
	@Override
	public String getName() {
		return "Villager";
	}

	@Override
	public String getDescription() {
		return "Do Nothing";
	}

	@Override
	public boolean isWinner(Set<Character> characters) {
		return isAlive() && characters.stream().allMatch(Villager.class::isInstance);
	}
}
