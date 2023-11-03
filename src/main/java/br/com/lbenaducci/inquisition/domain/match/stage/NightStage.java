package br.com.lbenaducci.inquisition.domain.match.stage;

import br.com.lbenaducci.inquisition.domain.character.base.Character;
import br.com.lbenaducci.inquisition.domain.character.night.NightCharacter;
import br.com.lbenaducci.inquisition.domain.match.Registry;

import java.util.ArrayList;
import java.util.List;

public final class NightStage extends InitialStage<List<Registry>> {
	private final List<Registry> registries = new ArrayList<>();

	@Override
	protected Stage<?> nextEvent() {
		return new DiscussionStage();
	}

	@Override
	protected boolean canDoAction(Character character) {
		return character instanceof NightCharacter;
	}

	@Override
	public List<Registry> getResult() {
		return registries;
	}

	public void addRegistry(Registry registry) {
		registries.add(registry);
	}
}
