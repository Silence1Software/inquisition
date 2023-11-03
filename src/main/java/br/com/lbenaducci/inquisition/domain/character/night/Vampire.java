package br.com.lbenaducci.inquisition.domain.character.night;

import br.com.lbenaducci.inquisition.domain.character.base.Character;
import br.com.lbenaducci.inquisition.domain.match.Action;
import br.com.lbenaducci.inquisition.domain.match.Registry;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Vampire extends Character implements NightTargetAction {
	@Override
	public String getName() {
		return "Vampire";
	}

	@Override
	public String getDescription() {
		return "Sucks blood";
	}

	@Override
	public boolean isWinner(Set<Character> characters) {
		return isAlive() && characters.stream().filter(it -> !it.equals(this)).noneMatch(Character::isAlive);
	}

	@Override
	public List<Registry> onNightAction(Character target) {
		List<Registry> registries = new ArrayList<>(target.onDeath());
		if(!target.isAlive()) {
			registries.add(Registry.of(this, Action.KILL, target));
		}
		return registries;
	}
}
