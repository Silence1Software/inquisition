package br.com.lbenaducci.inquisition.domain.character;

import br.com.lbenaducci.inquisition.domain.player.Player;

public abstract sealed class CharacterInfo extends CharacterStatusControl permits Character {
	private Player player;

	public abstract String getName();

	public abstract String getDescription();

	public final Player getPlayer() {
		return player;
	}

	public final void setPlayer(Player player) {
		if(this.player != null) {
			this.player = player;
		}
	}
}
