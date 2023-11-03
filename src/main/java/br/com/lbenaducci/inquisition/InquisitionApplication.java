package br.com.lbenaducci.inquisition;

import br.com.lbenaducci.inquisition.domain.character.base.AbstractCharacter;
import br.com.lbenaducci.inquisition.domain.character.night.GroupNightAction;
import br.com.lbenaducci.inquisition.domain.character.night.NightTargetAction;
import br.com.lbenaducci.inquisition.domain.character.night.Vampire;
import br.com.lbenaducci.inquisition.domain.character.night.Witch;
import br.com.lbenaducci.inquisition.domain.lobby.InitialStageOption;
import br.com.lbenaducci.inquisition.domain.lobby.Lobby;
import br.com.lbenaducci.inquisition.domain.match.Match;
import br.com.lbenaducci.inquisition.domain.match.stage.EndStage;
import br.com.lbenaducci.inquisition.domain.match.stage.NightStage;
import br.com.lbenaducci.inquisition.domain.match.stage.Stage;
import br.com.lbenaducci.inquisition.domain.player.Player;

import java.util.List;
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

		Set<AbstractCharacter> characters = match.getCharacters();
		characters.forEach(System.out::println);
		System.out.println("---------------------------------------\n");

		Stage<?> currentStage = match.getCurrentStage();
		if(currentStage instanceof NightStage nightStage) {
			for(AbstractCharacter character: match.getSequenceAction()) {
				try {
					if(character instanceof NightTargetAction vampire) {
							AbstractCharacter nonVampire = characters.stream().filter(it -> !(it instanceof Vampire)).findFirst().orElse(null);
							System.out.println(vampire + " kills " + nonVampire);
							nightStage.action(vampire, (v) -> v.action(nonVampire));
					}
					if(character instanceof GroupNightAction witch) {
						AbstractCharacter nonWitch = characters.stream().filter(it -> !(it instanceof Witch)).findFirst().orElse(null);
						System.out.println(witch + " vote " + nonWitch);
						nightStage.action(witch, (w, v) -> w.nightVote(v, nonWitch));
					}
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}

		Object result = match.result();
		if(result instanceof List<?> list) {
			list.forEach(System.out::println);
		}
		System.out.println("---------------------------------------\n");

		Stage<?> next = match.getCurrentStage();
		if(next instanceof EndStage end) {
			System.out.println("---------------------------------------\n");
			System.out.println("Winners = " + end.getResult());
		}
	}

}
