package dev.amil.cubo.framework;

import java.io.Serializable;

import dev.amil.cubo.framework.objects.Player;

/**
 * Classe que encapsula a comunicação entre o cliente e o servidor
 * @author junior
 *
 */
public class ComunicationWrapper implements Serializable{	
	private static final long serialVersionUID = 4469509462573053379L;
	public GameCommand getCommand() {
		return command;
	}
	public void setCommand(GameCommand command) {
		this.command = command;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public int getMatchID() {
		return matchID;
	}
	public void setMatchID(int matchID) {
		this.matchID = matchID;
	}
	private GameCommand command;
	private Player player;
	private int matchID;
	
	
}
