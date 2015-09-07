package dev.amil.cubo.framework.objects;

/**
 * Classe para controle do Hanking dos jogos
 * @author junior
 *
 */
public class Ranking{
	private Player player;
	private int kill;
	private int victm;
	private int awards;
	
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public int getKill() {
		return kill;
	}
	public void setKill(int kill) {
		this.kill = kill;
	}
	public int getVictm() {
		return victm;
	}
	public void setVictim(int victm) {
		this.victm = victm;
	}
	public int getAwards() {
		return awards;
	}
	public void setAward() {
		this.awards++;
	}	
}
