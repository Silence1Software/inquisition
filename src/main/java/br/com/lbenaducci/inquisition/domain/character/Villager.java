package br.com.lbenaducci.inquisition.domain.character;

import br.com.lbenaducci.inquisition.domain.character.base.AbstractCharacter;
import br.com.lbenaducci.inquisition.domain.character.base.Character;

import java.util.Set;

public class Villager extends AbstractCharacter {
	@Override
	public String getName() {
		return "Villager";
	}

	@Override
	public String getDescription() {
		return "Do Nothing";
	}

	@Override
	public boolean isWinner(Set<AbstractCharacter> characters) {
		return isAlive() && characters.stream().allMatch(Villager.class::isInstance);
	}
}
