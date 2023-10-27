package br.com.lbenaducci.inquisition.domain.match;

import br.com.lbenaducci.inquisition.domain.character.base.Character;

import java.util.List;

public record Register(
		Character character,
		Action action,
		List<Character> target
) {
	public static Register of(Character character, Action action, Character... target) {
		return new Register(character, action, List.of(target));
	}
}
