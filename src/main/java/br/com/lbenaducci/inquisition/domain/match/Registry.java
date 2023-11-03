package br.com.lbenaducci.inquisition.domain.match;

import br.com.lbenaducci.inquisition.domain.character.base.Character;

import java.util.List;

public record Registry(
		Character character,
		Action action,
		List<Character> target
) {
	public static Registry of(Character character, Action action, Character... target) {
		return new Registry(character, action, List.of(target));
	}
}
