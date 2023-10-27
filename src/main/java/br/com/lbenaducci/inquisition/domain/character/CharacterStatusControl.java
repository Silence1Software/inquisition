package br.com.lbenaducci.inquisition.domain.character;

public abstract sealed class CharacterStatusControl permits CharacterInfo {
	private CharacterStatus status;
	private boolean canVote;

	public final CharacterStatus getStatus() {
		return status;
	}

	public final boolean isAlive() {
		return getStatus() != CharacterStatus.DEAD;
	}

	public final void protect() {
		if(isAlive()) {
			this.status = CharacterStatus.PROTECTED;
		}
	}

	protected final void setStatus(CharacterStatus status) {
		this.status = status;
	}

	public final boolean canVote() {
		return canVote && isAlive();
	}

	public final void setCanVote(boolean canVote) {
		this.canVote = canVote;
	}
}
