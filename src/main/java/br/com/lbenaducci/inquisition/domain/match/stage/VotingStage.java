package br.com.lbenaducci.inquisition.domain.match.stage;

import br.com.lbenaducci.inquisition.domain.character.Character;
import br.com.lbenaducci.inquisition.domain.character.CharacterStatus;
import br.com.lbenaducci.inquisition.domain.match.MatchPlayer;
import br.com.lbenaducci.inquisition.domain.match.stage.dtos.VoteTurnCharacter;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public final class VotingStage extends Stage<Character, VoteTurnCharacter> {

	@Override
	protected DayStage nextEvent() {
		return new DayStage();
	}

	@Override
	public VoteTurnCharacter toTurnCharacter(MatchPlayer matchPlayer) {
		return new VoteTurnCharacter(matchPlayer, matchPlayer.getCharacter().canVote());
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
		                                          .map(VoteTurnCharacter::getMatchPlayer)
		                                          .map(MatchPlayer::getCharacter)
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
		              .filter(it -> it.getMatchPlayer().getCharacter().equals(voter) || it.getMatchPlayer().getCharacter().equals(target))
		              .forEach(it -> {
			              if(it.getMatchPlayer().getCharacter().equals(voter)) {
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
		if(targetCharacter == null || targetCharacter.getMatchPlayer().getCharacter().getStatus() == CharacterStatus.DEAD) {
			throw new IllegalArgumentException("Character cannot voted");
		}
		targetCharacter.vote();
		voterCharacter.setCanDoAction(false);
	}
}
