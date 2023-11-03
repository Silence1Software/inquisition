package br.com.lbenaducci.inquisition.domain.character.night;

import br.com.lbenaducci.inquisition.domain.character.base.AbstractCharacter;
import br.com.lbenaducci.inquisition.domain.match.Registry;

import java.util.List;

public interface NightTargetAction extends NightCharacter {
	List<Registry> action(AbstractCharacter target);
}
