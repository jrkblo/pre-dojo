package dev.amil.cubo.control;

import static java.util.Comparator.comparing;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.amil.cubo.framework.objects.Gun;
import dev.amil.cubo.framework.objects.Ranking;
import dev.amil.cubo.framework.objects.Match;
import dev.amil.cubo.framework.objects.Player;

/**
 * Classe que contém toda a inteligência do jogo
 * Pode ser movida para o servidor
 * @author junior
 *
 */
public class GameController {
	private Match match;
	private static Logger LOGGER = LoggerFactory.getLogger(GameController.class);
	private List<KillController> listKillController = new ArrayList<KillController>();

	public Match getMatch() {
		return match;
	}
	public Match create() throws Exception {
		match = Match.of((int) (Math.random() * 100000001));
		start();
		return match;
	}
	private void start() throws Exception {
		match.setDateStarted(Calendar.getInstance());
		LOGGER.info(String.format("New match %s has started", match.getId()));
	}
	public void stop() throws Exception {
		match.setDateFinished(Calendar.getInstance());
		LOGGER.info(String.format("Match %s has ended", match.getId()));
	}
	
	/**
	 * Quando o jogador faz um disparo, este método verifica se alguém foi atingido e retorna o jogador que foi atingido
	 * o retorno do método importa para classe que fica no cliente e é responsável por gerenciar o status do jogador
	 * @param p
	 * @return
	 */
	public Player checkShot(Player p) {		
		@SuppressWarnings("static-access")
		Optional<Player> repeated = match.getPlayerList().stream().filter(p1 -> p.getNick() != p1.getNick()//&& p1.isAlive()
						&& (p.getPosY() == p1.getPosY() || p.getPosX() == p1.getPosX())).findFirst();

		Player tryPlayer = null;
		if(repeated.isPresent()){
			tryPlayer = repeated.get();
			LOGGER.info(String.format("%s killed %s using %s", p.getNick(),tryPlayer.getNick(), p.getCurrentGun().getName()));
			tryPlayer.setAlive(false);
			listKillController.add(new KillController(p, tryPlayer, p.getCurrentGun(), Calendar.getInstance()));
		}
		return tryPlayer;
	}
	/**
	 * Quando o jogador se movimenta, este método verifica se há algum osbtaculo no caminho.
	 * O tratamento deve ser feito de acordo com o tipo de obstaculo atingido
	 * @param p
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean checkHitObstacle(Player p, int x, int y) {
		Rectangle rec = new Rectangle(x, y, p.getWidth(), p.getHeight());
		if (match.getMap().getObstacleList().stream().anyMatch(o -> o.bounds().intersects(rec))) {
			LOGGER.info(String.format("<WORLD> killed %s by DROWN", p.getNick()));
			listKillController.add(new KillController(null, p, null, Calendar.getInstance()));
			p.setAlive(false);
			return true;
		}
		return false;
	}
	
	/**
	 * Cria um ranking dos jogares por quantidade de assinatos
	 * 
	 *  Jogadores que vencerem uma partida sem morrerem devem ganhar um "award";
	 *  
	 * @param Objeto Game que contém os dados dos jogadores
	 * @return Ranking ordenado dos jogadores
	 */
	@SuppressWarnings("static-access")
	public List<Ranking> getRanking(Game g){		
		List<Ranking> lstH = new ArrayList<Ranking>();
		match.getPlayerList().forEach(p ->{
			Ranking h = new Ranking();
			h.setPlayer(p);
			h.setKill((int)listKillController.stream().filter(item -> item.getKiller() != null && item.getKiller().getNick().equals(p.getNick())).count());
			h.setVictim((int)listKillController.stream().filter(item -> item.getVictim().getNick().equals(p.getNick())).count());
			if(h.getVictm() == 0)
				h.setAward();
			
			lstH.add(h);
		});	
		Collections.sort(lstH, comparing(Ranking::getKill).reversed());
		return lstH;
	}
	/**
	 * Retorna a arma mais utilizada
	 * Descobrir a arma preferida (a que mais matou) do vencedor;
	 * @param Objeto Game que contém os dados dos jogadores
	 * @return Arma mais utilizada
	 */
	public Gun getPreferdGun(Game g) {
		Map<Gun, Long> lstCount = listKillController.stream().filter(gun -> gun.getKiller() != null) .collect(Collectors.groupingBy(KillController::getGun, Collectors.counting()));
		Map.Entry<Gun, Long> p = lstCount.entrySet().stream().max(Map.Entry.comparingByValue()).get();
		return p.getKey();
	}
	/**
	 * Método que verifica a quantidade assinatos feitos por cada jogador antes de ser assassinado e coloca em uma lista de rankings
	 * 
	 * Identificar a maior sequência de assassinatos efetuadas por um jogador (streak) sem morrer, dentro da partida;
	 * @param g
	 * @return
	 */ 
	@SuppressWarnings("static-access")
	public List<Ranking> getBestPlayer(Game g) {		
		List<Ranking> lstH = new ArrayList<Ranking>();
		List<Player> playerList = match.getPlayerList();
		
		int control = 0;
		int contKill = 0;
		for (Player player : playerList) {
			Ranking h = new Ranking();
			for (int i = 0; i < listKillController.size(); i++) {
				h.setPlayer(player);
				if(listKillController.get(i).getKiller() != null && listKillController.get(i).getKiller().getNick().equals(player.getNick())){
					contKill++;
					for (int j = i + 1; j < listKillController.size(); j++) {						
						h.setKill(getSumKill(listKillController, i, j));
						lstH.add(h);
						
						h = new Ranking();
						h.setPlayer(player);
						control++;
						i = j;
						break;
					}
				}
			}
			if(contKill == 0){
				h.setKill(listKillController.size());
				lstH.add(h);
				h = new Ranking();
			}else if(control == 0){
				h.setKill(listKillController.size());
				lstH.add(h);
				h = new Ranking();
			}
		}		
		Collections.sort(lstH, comparing(Ranking::getKill).reversed());
		return lstH;
	}
	/**
	 * Método auxiliar do método public List<Ranking> getBestPlayer(Game g)
	 * @param lst
	 * @param from
	 * @param to
	 * @return
	 */
	private int getSumKill(List<KillController> lst, int from, int to){
		Player p = lst.get(from).getKiller();
		int contKill = 0;
		for(int i = from; i <= to; i++){
			if(p == null){
				p = lst.get(i).getKiller();
				continue;
			}
			if(lst.get(i).getKiller() != null && p.getNick().equals(lst.get(i).getKiller().getNick())){				
				contKill++;
			}
		}
		return contKill;
	}

	// * Jogadores que matarem 5 vezes em 1 minuto devem ganhar um "award".
	public void setPlayerAwardBestShot(Game g) {		

	}

}