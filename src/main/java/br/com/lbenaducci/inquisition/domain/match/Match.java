package br.com.lbenaducci.inquisition.domain.match;

import br.com.lbenaducci.inquisition.domain.character.Character;
import br.com.lbenaducci.inquisition.domain.generic.GenericEntity;
import br.com.lbenaducci.inquisition.domain.player.Player;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.SequencedSet;
import java.util.Set;

public class Match extends GenericEntity {
	private final Set<Character> characters;
	private final SequencedSet<Log> logs;
	private MatchStatus status;

	public Match(Set<Player> players) {
		this.characters = CharacterRandomizer.randomize(players);
		this.logs = new LinkedHashSet<>();
		status = MatchStatus.RUNNING;
		validate();
	}

	@Override
	public void validate() {
		if(characters == null) {
			throw new IllegalArgumentException("Characters cannot be null");
		}
		if(logs == null) {
			throw new IllegalArgumentException("Logs cannot be null");
		}
		if(status == null) {
			throw new IllegalArgumentException("Status cannot be null");
		}
		if(characters.size() < 5) {
			throw new IllegalArgumentException("Must have at least 5 characters");
		}
	}

	public Set<Character> getCharacters() {
		return Collections.unmodifiableSet(characters);
	}

	// dia -> noite -> evento -> discussão -> votação <-

	public SequencedSet<Log> getLogs() {
		return Collections.unmodifiableSequencedSet(logs);
	}

	public MatchStatus getStatus() {
		return status;
	}

	public void setStatus(MatchStatus status) {
		this.status = status;
	}
}
