package br.com.lbenaducci.inquisition.domain.character.night;

import br.com.lbenaducci.inquisition.domain.character.base.Character;
import br.com.lbenaducci.inquisition.domain.match.Registry;
import br.com.lbenaducci.inquisition.domain.match.stage.NightStage;

import java.util.List;

public interface NightTargetAction extends NightCharacter {
	List<Registry> onNightAction(Character target);

	default void nightAction(Character target, NightStage stage) {
		List<Registry> registries = onNightAction(target);
		addNightRegistry(stage, registries);
	}
}
