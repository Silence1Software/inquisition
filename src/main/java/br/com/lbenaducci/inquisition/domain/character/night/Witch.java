package br.com.lbenaducci.inquisition.domain.character.night;

import br.com.lbenaducci.inquisition.domain.character.base.AbstractCharacter;
import br.com.lbenaducci.inquisition.domain.character.base.Character;

import java.util.Set;

public class Witch extends AbstractCharacter implements NightCharacter {

	@Override
	public String getName() {
		return "Witch";
	}

	@Override
	public String getDescription() {
		return "Kill Villagers";
	}

	@Override
	public boolean isWinner(Set<AbstractCharacter> characters) {
		return isAlive() && characters.stream().allMatch(Witch.class::isInstance);
	}
}
