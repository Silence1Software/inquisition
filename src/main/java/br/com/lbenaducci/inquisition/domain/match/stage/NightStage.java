package br.com.lbenaducci.inquisition.domain.match.stage;

import br.com.lbenaducci.inquisition.domain.character.base.AbstractCharacter;
import br.com.lbenaducci.inquisition.domain.character.night.NightCharacter;
import br.com.lbenaducci.inquisition.domain.match.Action;
import br.com.lbenaducci.inquisition.domain.match.Registry;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public final class NightStage extends VotingStage<List<Registry>> implements ActionStage<NightCharacter>, InitialStage {
	private final List<Registry> registries = new ArrayList<>();

	@Override
	protected DiscussionStage nextEvent() {
		return new DiscussionStage();
	}

	@Override
	protected boolean canDoAction(AbstractCharacter character) {
		return character instanceof NightCharacter;
	}

	@Override
	protected List<Registry> getResult() {
		AbstractCharacter pobreCoitado = voting.getWinner();
		if(pobreCoitado != null) {
			registries.addAll(pobreCoitado.onDeath());
		}
		return registries;
	}

	@Override
	public <O extends NightCharacter> void action(O character, Function<O, List<Registry>> action) {
		if(!character.isAlive()) {
			throw new IllegalArgumentException("Character is not alive");
		}
		if(character.equals(nextCharacter())) {
			List<Registry> actionRegistries = action.apply(character);
			registries.addAll(actionRegistries.stream().filter(it -> it.action() == Action.KILL).toList());
			removeQueue(character);
			for(Registry registry: actionRegistries) {
				for(AbstractCharacter target: registry.target()) {
					if(!target.isAlive()) {
						removeQueue(target);
					}
				}
			}
		} else {
			throw new IllegalArgumentException("Character is not a next character");
		}
	}
}
