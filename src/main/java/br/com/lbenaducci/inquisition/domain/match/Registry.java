package br.com.lbenaducci.inquisition.domain.match;

import br.com.lbenaducci.inquisition.domain.character.base.AbstractCharacter;

import java.util.List;

public record Registry(
		AbstractCharacter character,
		Action action,
		List<AbstractCharacter> target
) {
	public static Registry of(AbstractCharacter character, Action action, AbstractCharacter... target) {
		return new Registry(character, action, List.of(target));
	}
}
