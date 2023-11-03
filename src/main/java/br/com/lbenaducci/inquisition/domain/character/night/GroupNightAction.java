package br.com.lbenaducci.inquisition.domain.character.night;

import br.com.lbenaducci.inquisition.domain.character.base.AbstractCharacter;
import br.com.lbenaducci.inquisition.domain.match.Voting;

public interface GroupNightAction extends NightCharacter {
	void nightVote(Voting voting, AbstractCharacter target);
}
