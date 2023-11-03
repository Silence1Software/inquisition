package br.com.lbenaducci.inquisition.domain.character.night;

import br.com.lbenaducci.inquisition.domain.match.Action;
import br.com.lbenaducci.inquisition.domain.match.Registry;
import br.com.lbenaducci.inquisition.domain.match.stage.NightStage;

import java.util.List;

public interface NightCharacter {
	default void addNightRegistry(NightStage stage, List<Registry> actionRegistries) {
		for(Registry registry: actionRegistries) {
			if(registry.action() == Action.KILL) {
				stage.addRegistry(registry);
			}
		}
	}
}

