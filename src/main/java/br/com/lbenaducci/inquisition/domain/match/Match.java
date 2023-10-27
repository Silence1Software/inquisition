package br.com.lbenaducci.inquisition.domain.match;

import br.com.lbenaducci.inquisition.domain.character.Character;
import br.com.lbenaducci.inquisition.domain.generic.GenericEntity;
import br.com.lbenaducci.inquisition.domain.match.stage.InitialStage;
import br.com.lbenaducci.inquisition.domain.match.stage.Stage;
import br.com.lbenaducci.inquisition.domain.player.Player;

import java.util.Collections;
import java.util.SequencedSet;
import java.util.Set;

public class Match extends GenericEntity {
	private final Set<Character> characters;
	private MatchStatus status;
	private Stage<?> currentStage;

	public Match(Set<Player> players, InitialStage<?> initialStage) {
		this.characters = CharacterRandomizer.randomize(players);
		status = MatchStatus.RUNNING;
		currentStage = initialStage;
		InitialStage.start(initialStage, characters);
		validate();
	}

	@Override
	public void validate() {
		if(characters == null) {
			throw new IllegalArgumentException("Characters cannot be null");
		}
		if(status == null) {
			throw new IllegalArgumentException("Status cannot be null");
		}
		if(characters.size() < 5) {
			throw new IllegalArgumentException("Must have at least 5 characters");
		}
		if(currentStage == null) {
			throw new IllegalArgumentException("Current stage cannot be null");
		}
	}

	public SequencedSet<Character> getSequenceAction() {
		return currentStage.getSequenceAction();
	}

	public Stage<?> getCurrentStage() {
		return currentStage;
	}

	public Set<Character> getCharacters() {
		return Collections.unmodifiableSet(characters);
	}

	public MatchStatus getStatus() {
		return status;
	}

	public void setStatus(MatchStatus status) {
		this.status = status;
	}
}
