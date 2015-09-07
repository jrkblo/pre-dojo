package gameproject.game;

import java.util.List;

import dev.amil.cubo.control.Game;
import dev.amil.cubo.framework.objects.Gun;
import dev.amil.cubo.framework.objects.Ranking;


public class Report {
	//A partir de um input de um arquivo de log do formato acima, montar o ranking de cada partida, com a quantidade assassinatos e a quantidade de mortes de cada jogador;
	//Jogadores que vencerem uma partida sem morrerem devem ganhar um "award";
	public void generateRanking(Game g){		
		List<Ranking> hanking = g.getRanking(g);
		hanking.forEach(r ->{
			System.out.println(String.format("Jogador %s; Assinatos %s; Mortes %s", r.getPlayer(), r.getKill(), r.getVictm()));
		});		
	}
	//Descobrir a arma preferida (a que mais matou) do vencedor;
	public void generatePreferedGun(Game g){
		Gun prefered = g.getPreferedGun(g);
		System.out.println(String.format("Arma preferida do melhor jogador: %s", prefered.getName()));
	}
	
	//Identificar a maior sequência de assassinatos efetuadas por um jogador (streak) sem morrer, dentro da partida;
	public void generateBestSequence(Game g){
		List<Ranking> bestPlayer = g.getBestPlayer(g);
		System.out.println(String.format("A melhor sequencia foi: %s assassinatos", bestPlayer.get(0).getKill()));
	}
	//Jogadores que matarem 5 vezes em 1 minuto devem ganhar um "award".
	public void generate5Kill(){
		System.out.println("Não implementado");
	}
}