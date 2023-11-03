package br.com.lbenaducci.inquisition.domain.match.stage;

import br.com.lbenaducci.inquisition.domain.character.base.Character;
import br.com.lbenaducci.inquisition.domain.match.Registry;

import java.util.List;
import java.util.function.Function;

public interface ActionStage<T extends Character> {
	<O extends T> void action(O character, Function<O, List<Registry>> action);
}
