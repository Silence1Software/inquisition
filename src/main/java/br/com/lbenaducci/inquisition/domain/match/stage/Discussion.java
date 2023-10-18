package br.com.lbenaducci.inquisition.domain.match.stage;

public final class Discussion implements Stage{
	@Override
	public Stage next() {
		return new Voting();
	}
}
