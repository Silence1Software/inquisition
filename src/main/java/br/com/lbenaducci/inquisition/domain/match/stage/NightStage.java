package br.com.lbenaducci.inquisition.domain.match.stage;

import br.com.lbenaducci.inquisition.domain.character.base.AbstractCharacter;
import br.com.lbenaducci.inquisition.domain.character.night.NightCharacter;
import br.com.lbenaducci.inquisition.domain.match.Action;
import br.com.lbenaducci.inquisition.domain.match.Registry;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class NightStage extends InitialStage<List<Registry>> implements ActionStage<NightCharacter> {
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
		return registries;
	}

	@Override
	public <O extends NightCharacter> void action(O character, Function<O, List<Registry>> action) {
		if(character.equals(nextCharacter())){
			List<Registry> actionRegistries = action.apply(character);
			registries.addAll(actionRegistries.stream().filter(it -> it.action() == Action.KILL).toList());
			removeQueue(character);
		}else {
			throw new IllegalArgumentException("Character is not a next character");
		}
	}
}
