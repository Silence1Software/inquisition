package br.com.lbenaducci.inquisition;

import br.com.lbenaducci.inquisition.domain.lobby.Lobby;
import br.com.lbenaducci.inquisition.domain.match.Match;
import br.com.lbenaducci.inquisition.domain.player.Player;

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

		match.getMatchPlayers().forEach(System.out::println);
	}

}
