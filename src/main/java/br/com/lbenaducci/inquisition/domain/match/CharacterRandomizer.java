package br.com.lbenaducci.inquisition.domain.match;

import br.com.lbenaducci.inquisition.domain.character.Character;
import br.com.lbenaducci.inquisition.domain.character.Vampire;
import br.com.lbenaducci.inquisition.domain.character.Villager;
import br.com.lbenaducci.inquisition.domain.character.Witch;
import br.com.lbenaducci.inquisition.domain.player.Player;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Set;

public class CharacterRandomizer {
	private CharacterRandomizer() throws IllegalAccessException {
		throw new IllegalAccessException("Utility class");
	}

	public static Set<Character> randomize(Set<Player> players) {
		Set<Character> matchPlayers = new HashSet<>();

		SecureRandom random = new SecureRandom();
		int witchRng = random.nextInt(players.size());
		int vampireRng = random.nextInt(players.size());
		while(vampireRng == witchRng) {
			vampireRng = random.nextInt(players.size());
		}

		int i = 0;
		for(Player player: players) {
			Character character;
			if(i == witchRng) {
				character = new Witch();
			} else if(i == vampireRng) {
				character = new Vampire();
			} else {
				character = new Villager();
			}
			i++;
			character.setPlayer(player);
			matchPlayers.add(character);
		}

		return matchPlayers;
	}
}
