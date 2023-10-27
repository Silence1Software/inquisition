package br.com.lbenaducci.inquisition.domain.character.night;

import br.com.lbenaducci.inquisition.domain.character.base.Character;

import java.util.Set;

public class Witch extends Character implements NightCharacter {

	@Override
	public String getName() {
		return "Witch";
	}

	@Override
	public String getDescription() {
		return "Kill Villagers";
	}

	@Override
	public boolean isWinner(Set<Character> characters) {
		return isAlive() && characters.stream().allMatch(Witch.class::isInstance);
	}
}
