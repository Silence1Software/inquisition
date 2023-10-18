package br.com.lbenaducci.inquisition.domain.match.stage;

public final class Night implements Stage{
	@Override
	public Stage next() {
		return new Event();
	}
}
