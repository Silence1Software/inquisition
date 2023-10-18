package br.com.lbenaducci.inquisition.domain.lobby;

import br.com.lbenaducci.inquisition.domain.match.Match;
import br.com.lbenaducci.inquisition.domain.generic.GenericEntity;
import br.com.lbenaducci.inquisition.domain.player.Player;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Lobby extends GenericEntity {
	private final Player host;
	private final Set<Player> players;
	private String secret;

	public Lobby(Player host) {
		this(null, host, new HashSet<>(), UUID.randomUUID().toString());
	}

	public Lobby(UUID id, Player host, Set<Player> players, String secret) {
		super(id);
		this.host = host;
		this.players = players;
		this.secret = secret;
		validate();
	}

	@Override
	public void validate() {
		if(host == null) {
			throw new IllegalArgumentException("Host cannot be null");
		}
		if(players == null) {
			throw new IllegalArgumentException("Players cannot be null");
		}
		if(secret == null) {
			throw new IllegalArgumentException("Secret cannot be null");
		}
	}

	public void addPlayer(Player... players) {
		Collections.addAll(this.players, players);
	}

	public void removePlayer(Player... players) {
		for(Player player: players) {
			this.players.remove(player);
		}
	}

	public void generateNewSecret() {
		this.secret = UUID.randomUUID().toString();
	}

	public Match createMatch() {
		return new Match(players);
	}

	public Player getHost() {
		return host;
	}

	public Set<Player> getPlayers() {
		Set<Player> tempPlayers = new HashSet<>(this.players);
		tempPlayers.add(host);
		return Collections.unmodifiableSet(tempPlayers);
	}

	public String getSecret() {
		return secret;
	}
}
