package br.com.lbenaducci.inquisition.domain.match.stage;

import br.com.lbenaducci.inquisition.domain.character.Character;
import br.com.lbenaducci.inquisition.domain.character.CharacterStatus;
import br.com.lbenaducci.inquisition.domain.match.stage.dtos.VoteCharacter;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public final class Voting extends Stage<Character, VoteCharacter> {

	@Override
	protected Day nextEvent() {
		return new Day();
	}

	@Override
	public VoteCharacter toTurnCharacter(Character character) {
		return new VoteCharacter(character, character.canVote());
	}

	@Override
	public Character getResult() {
		if(turnCharacters.stream().anyMatch(VoteCharacter::canDoAction)) {
			throw new IllegalArgumentException("All characters must vote");
		}
		Integer max = turnCharacters.stream()
		                   .map(VoteCharacter::getVotes)
		                   .max(Integer::compareTo)
		                   .orElse(null);
		List<Character> mostVoted = turnCharacters.stream()
		                                 .filter(it -> it.getVotes() == max)
		                                 .map(VoteCharacter::getCharacter)
		                                 .toList();
		if(mostVoted.size() > 1) {
			return null;
		}
		Character kicked = mostVoted.getFirst();
		kicked.onKicked();
		return kicked;
	}

	public void vote(Character voter, Character target) {
		AtomicReference<VoteCharacter> atmVoter = new AtomicReference<>();
		AtomicReference<VoteCharacter> atmTarget = new AtomicReference<>();
		turnCharacters.stream()
		     .filter(it -> it.getCharacter().equals(voter) || it.getCharacter().equals(target))
		     .forEach(it -> {
			     if(it.getCharacter().equals(voter)) {
				     atmVoter.set(it);
			     } else {
				     atmTarget.set(it);
			     }
		     });
		VoteCharacter voterCharacter = atmVoter.get();
		VoteCharacter targetCharacter = atmTarget.get();
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
