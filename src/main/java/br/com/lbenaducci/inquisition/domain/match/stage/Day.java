package br.com.lbenaducci.inquisition.domain.match.stage;

public final class Day implements Stage{
	@Override
	public Stage next() {
		return new Night();
	}
}
