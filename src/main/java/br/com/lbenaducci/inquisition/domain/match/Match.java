package br.com.lbenaducci.inquisition.domain.match;

import br.com.lbenaducci.inquisition.domain.character.base.AbstractCharacter;
import br.com.lbenaducci.inquisition.domain.generic.GenericEntity;
import br.com.lbenaducci.inquisition.domain.match.stage.InitialStage;
import br.com.lbenaducci.inquisition.domain.match.stage.Stage;
import br.com.lbenaducci.inquisition.domain.player.Player;

import java.util.Collections;
import java.util.SequencedSet;
import java.util.Set;

public class Match extends GenericEntity {
	private final Set<AbstractCharacter> characters;
	private MatchStatus status;
	private Stage<?> currentStage;

	public <S extends Stage<?> & InitialStage> Match(Set<Player> players, S initialStage) {
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

	public SequencedSet<AbstractCharacter> getSequenceAction() {
		return currentStage.getSequenceAction();
	}

	public Stage<?> getCurrentStage() {
		return currentStage;
	}

	public Object result() {
		Object result = currentStage.result();
		this.currentStage = currentStage.nextStage();
		return result;
	}

	public Set<AbstractCharacter> getCharacters() {
		return Collections.unmodifiableSet(characters);
	}

	public MatchStatus getStatus() {
		return status;
	}

	public void setStatus(MatchStatus status) {
		this.status = status;
	}
}
