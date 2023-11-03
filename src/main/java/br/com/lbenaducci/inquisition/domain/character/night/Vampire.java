package br.com.lbenaducci.inquisition.domain.character.night;

import br.com.lbenaducci.inquisition.domain.character.base.AbstractCharacter;
import br.com.lbenaducci.inquisition.domain.character.base.Character;
import br.com.lbenaducci.inquisition.domain.match.Action;
import br.com.lbenaducci.inquisition.domain.match.Registry;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Vampire extends AbstractCharacter implements NightTargetAction {
	@Override
	public String getName() {
		return "Vampire";
	}

	@Override
	public String getDescription() {
		return "Sucks blood";
	}

	@Override
	public boolean isWinner(Set<AbstractCharacter> characters) {
		return isAlive() && characters.stream().filter(it -> !it.equals(this)).noneMatch(AbstractCharacter::isAlive);
	}

	@Override
	public List<Registry> action(AbstractCharacter target) {
		List<Registry> registries = new ArrayList<>(target.onDeath());
		if(!target.isAlive()) {
			registries.add(Registry.of(this, Action.KILL, target));
		}
		return registries;
	}
}
