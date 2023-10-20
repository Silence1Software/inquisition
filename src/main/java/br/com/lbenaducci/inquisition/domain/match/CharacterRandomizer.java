package br.com.lbenaducci.inquisition.domain.match;

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

	public static Set<MatchPlayer> randomize(Set<Player> players) {
		Set<MatchPlayer> matchPlayers = new HashSet<>();

		SecureRandom random = new SecureRandom();
		int witch = random.nextInt(players.size());
		int vampire = random.nextInt(players.size());
		while(vampire == witch) {
			vampire = random.nextInt(players.size());
		}

		int i = 0;
		for(Player player: players) {
			if(i == witch) {
				matchPlayers.add(new MatchPlayer(player, new Witch()));
			} else if(i == vampire) {
				matchPlayers.add(new MatchPlayer(player, new Vampire()));
			} else {
				matchPlayers.add(new MatchPlayer(player, new Villager()));
			}
			i++;
		}

		return matchPlayers;
	}
}
