package dev.amil.cubo.framework.objects;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

/**
 * Classe que controla cada partida do jogo
 * Podem ocorrer diversas partidas simultaneas, cada uma com seus respectivos jogadores e mapas
 * @author junior
 *
 */
public class Match implements Serializable{

	private static final long serialVersionUID = -6161201860836428923L;
	private final int id;
	private static List<Player> playerList = Lists.newLinkedList();	
	private Calendar dateStarted;
	private Calendar dateFinished;
	private Map map;
	
	public Match(int id){
		this.id = id;
		map = Map.create();
		this.playerList.clear();
	}
	
	public static Match of(int id){		
		return new Match(id);
	}

	public static List<Player> getPlayerList() {		
		return ImmutableList.copyOf(playerList);
	}
	public static void addPlayer(Player player) throws Exception {		
		checkNotNull(player);
		Optional<Player> repeated = playerList.stream().filter(i -> i.getNick().equals(player.getNick())).findAny();
		if(repeated.isPresent())
			throw new Exception("Ja existe um usuário com este nome");
		else			
			playerList.add(player);
	}
	
	public Map getMap(){
		return this.map;
	}
	
	public Calendar getDateStarted() {
		return dateStarted;
	}
	public void setDateStarted(Calendar dateStarted) {
		this.dateStarted = dateStarted;
	}
	public Calendar getDateFinished() {
		return dateFinished;
	}
	public void setDateFinished(Calendar dateFinished) {
		this.dateFinished = dateFinished;
	}
	public int getId() {
		return id;
	}	
}
