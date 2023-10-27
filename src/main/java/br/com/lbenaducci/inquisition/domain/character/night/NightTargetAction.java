package br.com.lbenaducci.inquisition.domain.character.night;

import br.com.lbenaducci.inquisition.domain.character.base.Character;
import br.com.lbenaducci.inquisition.domain.match.Register;

import java.util.List;

public interface NightTargetAction extends NightCharacter {
	List<Register> onNightAction(Character target);

	default void nightAction(Character target) {
		onNightAction(target);
	}
}
