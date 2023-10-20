package br.com.lbenaducci.inquisition.domain.match.stage;

import br.com.lbenaducci.inquisition.domain.character.Character;
import br.com.lbenaducci.inquisition.domain.character.CharacterStatus;
import br.com.lbenaducci.inquisition.domain.match.stage.dtos.VoteTurnCharacter;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public final class Voting extends Stage<Character, VoteTurnCharacter> {

	@Override
	protected Day nextEvent() {
		return new Day();
	}

	@Override
	public VoteTurnCharacter toTurnCharacter(Character character) {
		return new VoteTurnCharacter(character, character.canVote());
	}

	@Override
	public Character getResult() {
		if(turnCharacters.stream().anyMatch(VoteTurnCharacter::canDoAction)) {
			throw new IllegalArgumentException("All characters must vote");
		}
		Integer max = turnCharacters.stream()
		                   .map(VoteTurnCharacter::getVotes)
		                   .max(Integer::compareTo)
		                   .orElse(null);
		List<Character> mostVoted = turnCharacters.stream()
		                                 .filter(it -> it.getVotes() == max)
		                                 .map(VoteTurnCharacter::getCharacter)
		                                 .toList();
		if(mostVoted.size() > 1) {
			return null;
		}
		Character kicked = mostVoted.getFirst();
		kicked.onKicked();
		return kicked;
	}

	public void vote(Character voter, Character target) {
		AtomicReference<VoteTurnCharacter> atmVoter = new AtomicReference<>();
		AtomicReference<VoteTurnCharacter> atmTarget = new AtomicReference<>();
		turnCharacters.stream()
		     .filter(it -> it.getCharacter().equals(voter) || it.getCharacter().equals(target))
		     .forEach(it -> {
			     if(it.getCharacter().equals(voter)) {
				     atmVoter.set(it);
			     } else {
				     atmTarget.set(it);
			     }
		     });
		VoteTurnCharacter voterCharacter = atmVoter.get();
		VoteTurnCharacter targetCharacter = atmTarget.get();
		if(voterCharacter == null || !voterCharacter.canDoAction()) {
			throw new IllegalArgumentException("Character cannot vote");
		}
		if(targetCharacter == null || targetCharacter.getCharacter().getStatus() == CharacterStatus.DEAD) {
			throw new IllegalArgumentException("Character cannot voted");
		}
		targetCharacter.vote();
		voterCharacter.setCanDoAction(false);
	}
}
