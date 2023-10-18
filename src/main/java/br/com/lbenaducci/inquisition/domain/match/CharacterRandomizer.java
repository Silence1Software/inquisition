package br.com.lbenaducci.inquisition.domain.match;

import br.com.lbenaducci.inquisition.domain.character.Character;
import br.com.lbenaducci.inquisition.domain.player.Player;

import java.util.HashSet;
import java.util.Set;

public class CharacterRandomizer {
	private CharacterRandomizer() throws IllegalAccessException {
		throw new IllegalAccessException("Utility class");
	}

	public static Set<Character> randomize(Set<Player> players) {
		return new HashSet<>();
	}
}
