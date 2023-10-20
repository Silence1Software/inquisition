package br.com.lbenaducci.inquisition.domain.character;

import java.util.Set;

public class Vampire extends Character implements NightCharacter {
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
	public void onKicked() {
		super.onKicked();
	}

	@Override
	public void onDeath() {
		super.onDeath();
	}

	@Override
	public void nightAction() {

	}
}
