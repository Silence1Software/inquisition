package br.com.lbenaducci.inquisition;

import br.com.lbenaducci.inquisition.domain.character.base.Character;
import br.com.lbenaducci.inquisition.domain.character.night.NightTargetAction;
import br.com.lbenaducci.inquisition.domain.character.night.Vampire;
import br.com.lbenaducci.inquisition.domain.lobby.InitialStageOption;
import br.com.lbenaducci.inquisition.domain.lobby.Lobby;
import br.com.lbenaducci.inquisition.domain.match.Match;
import br.com.lbenaducci.inquisition.domain.match.stage.EndStage;
import br.com.lbenaducci.inquisition.domain.match.stage.NightStage;
import br.com.lbenaducci.inquisition.domain.match.stage.Stage;
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
		lobby.setInitialStage(InitialStageOption.NIGHT);

		Match match = lobby.createMatch();

		Set<Character> characters = match.getCharacters();
		characters.forEach(System.out::println);
		System.out.println("---------------------------------------\n");

		Stage<?> currentStage = match.getCurrentStage();
		for(Character character: match.getSequenceAction()) {
			if(currentStage instanceof NightStage nightStage) {
				if(character instanceof NightTargetAction vampire) {
					Character nonVampire = characters.stream().filter(it -> !(it instanceof Vampire)).findFirst().orElse(null);
					vampire.nightAction(nonVampire, nightStage);
				}
			}
		}

		Object result = currentStage.getResult();
		System.out.println("---------------------------------------\n");
		System.out.println(result);

		Stage<?> next = currentStage.next();
		if(next instanceof EndStage end) {
			System.out.println("---------------------------------------\n");
			System.out.println("Winners = " + end.getResult());
		}
	}

}
