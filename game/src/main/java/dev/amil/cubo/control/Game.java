package dev.amil.cubo.control;

import java.util.ArrayList;
import java.util.List;

import dev.amil.cubo.framework.objects.Gun;
import dev.amil.cubo.framework.objects.Ranking;
import dev.amil.cubo.framework.objects.Match;
import dev.amil.cubo.framework.objects.Player;
/**
 * Classe que controla o jogo do lado do cliente
 * Muitos de seus métodos apenas fazem referencia para os métodos da classe principal
 * GameController pode ser gerado no servidor
 * @author junior
 *
 */
public class Game {
	private List<Player> playerList = new ArrayList<Player>();
	private boolean isRunning = false;
	private Match match;
	private GameController gc = new GameController();
	
	public synchronized void start() throws Exception{		
		if(isRunning)
			return;
		isRunning = true;
		init();
	}	
	private void init() throws Exception {
		if(match == null)
			match = gc.create();
	}
	@SuppressWarnings("static-access")
	public void addPlayer(Player player) throws Exception {		
		match.addPlayer(player);
		playerList.add(player);
	}
	public List<Player> getPlayerList(){
		return this.playerList;
	}
	public Player shot(Player p){
		return gc.checkShot(p);
	}
	public boolean checkHitObstacle(Player p, int x, int y){
		return gc.checkHitObstacle(p, x, y);		
	}
	public void stop() throws Exception{
		gc.stop();
	}
	public Gun getPreferedGun(Game g){
		return gc.getPreferdGun(g);
	}
	public List<Ranking> getRanking(Game g){
		return gc.getRanking(g);
	}
	public List<Ranking> getBestPlayer(Game g) {
		return gc.getBestPlayer(g);
	}
}
