package br.com.lbenaducci.inquisition;

import br.com.lbenaducci.inquisition.domain.character.Character;
import br.com.lbenaducci.inquisition.domain.lobby.Lobby;
import br.com.lbenaducci.inquisition.domain.match.Match;
import br.com.lbenaducci.inquisition.domain.match.stage.VotingStage;
import br.com.lbenaducci.inquisition.domain.player.Player;

import java.util.Set;

//@SpringBootApplication
public class InquisitionApplication {

	public static void main(String[] args) {
		//		SpringApplication.run(InquisitionApplication.class, args);

		Player leo = new Player("Leo");
		Player david = new Player("David");
		Player diego = new Player("Diego");
		Player paulo = new Player("Paulo");
		Player ana = new Player("Ana");

		Lobby lobby = new Lobby(leo);
		lobby.addPlayer(david, diego, paulo, ana);

		Match match = lobby.createMatch();

		Set<Character> characters = match.getCharacters();
		characters.forEach(System.out::println);
		Character characterAna = characters.stream().filter(it -> it.getPlayer().equals(ana)).findFirst().orElse(null);
		for(Character character: characters) {
			if(match.getCurrentStage() instanceof VotingStage votingStage) {
				character.vote(characterAna, votingStage.getVoting());
			}
		}
	}

}
