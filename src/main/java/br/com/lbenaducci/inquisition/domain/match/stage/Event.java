package br.com.lbenaducci.inquisition.domain.match.stage;

public final class Event implements Stage{
	@Override
	public Stage next() {
		return new Discussion();
	}
}
