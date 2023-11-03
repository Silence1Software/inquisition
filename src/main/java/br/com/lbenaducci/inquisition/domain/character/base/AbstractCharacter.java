package br.com.lbenaducci.inquisition.domain.character.base;

import br.com.lbenaducci.inquisition.domain.match.Action;
import br.com.lbenaducci.inquisition.domain.match.Registry;
import br.com.lbenaducci.inquisition.domain.match.Voting;

import java.util.List;
import java.util.Set;

public non-sealed abstract class AbstractCharacter extends CharacterInfo implements Character {

	public void onKicked() {
		setStatus(CharacterStatus.DEAD);
	}

	public List<Registry> onDeath() {
		if(getStatus() == CharacterStatus.PROTECTED) {
			setStatus(CharacterStatus.ALIVE);
			return List.of(Registry.of(this, Action.PROTECTED));
		} else {
			setStatus(CharacterStatus.DEAD);
			return List.of(Registry.of(this, Action.DIE));
		}
	}
}
