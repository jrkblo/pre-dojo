package gameproject.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import dev.amil.cubo.control.Game;
import dev.amil.cubo.framework.ObjectType;
import dev.amil.cubo.framework.objects.Gun;
import dev.amil.cubo.framework.objects.Ranking;
import dev.amil.cubo.framework.objects.Player;

/**
 * Teste unitário para Classe Game
 */
public class GameTest
{
	/**
	 * Teste simples seguindo a lógida do arquivo de LOG enviado
	 */
    @Test
    public void simpleTest(){
	    try {
			Game g = new Game();
			g.start();
			
			Gun gun = new Gun();
			gun.setName("M16");
			
			Player p1 = new Player(0, 0, ObjectType.PLAYER);
			p1.setNick("Roman");
			p1.setCurrentGun(gun);
			
			Player p2 = new Player(100, 0, ObjectType.PLAYER);
			p2.setNick("Nick");
			p1.setCurrentGun(gun);
			
			g.addPlayer(p1);
			g.addPlayer(p2);
			
			
			g.shot(p1);
			
			g.checkHitObstacle(p2, 10, 0);
			
			g.stop();
			
		} catch (Exception e) {
		
		}	
    }
    /**
     * Teste utilizando apenas os recursos de tiro
     */
    @Test
    public void complexTestShot(){
    	try {
    		Game g = new Game();    		
    		g.getPlayerList().clear();
    		g.start();
    		
    		createPlayerList().forEach(p ->{
    			try {
    				p.setCurrentGun(getRandom());
					g.addPlayer(p);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
    		});			

		      Thread read = new Thread();
		      synchronized (read) {
		    	  try{
		                shotMode(g);                
		                read.wait();
		            }catch(InterruptedException e){
		                e.printStackTrace();
		            }
			}
            read.setDaemon(true);
            read.start();
    		g.stop();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}    	
    }
    /**
     * Teste utilizando apenas mortes por afogamento
     * onde o jogador se movimenta para dentro do rio
     */
    @Test
    public void complexTestMove(){
    	try {
    		Game g = new Game();    		
    		g.getPlayerList().clear();
    		g.start();
    		
    		createPlayerList().forEach(p ->{
    			try {
    				p.setCurrentGun(getRandom());
					g.addPlayer(p);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
    		});			

		      Thread read = new Thread();
		      synchronized (read) {
		    	  try{
		                moveMode(g);                
		                //read.wait();
		            }catch(Exception e){
		                System.out.println(e.getMessage());
		            }
			}
            read.setDaemon(true);
            read.start();
    		g.stop();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}    	
    }
    /**
     * Teste completo, onde todos se movimentam e atiram e também cair no rio
     */
    @Test
    public void complexTestMoveShot(){
    	try {
    		Game g = new Game();    		
    		g.getPlayerList().clear();
    		g.start();
    		
    		createPlayerList().forEach(p ->{
    			try {
    				p.setCurrentGun(getRandom());
					g.addPlayer(p);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
    		});			

		      Thread read = new Thread();
		      synchronized (read) {
		    	  try{
		    		  shotMoveShotMode(g, 3000);                
		                //read.wait();
		            }catch(Exception e){
		                System.out.println(e.getMessage());
		            }
			}
            read.setDaemon(true);
            read.start();
    		g.stop();
    		
    		Report r = new Report();
    		r.generateBestSequence(g);
    		r.generatePreferedGun(g);
    		r.generateRanking(g);
    		r.generate5Kill(); 
    		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}   
    }
	/**
	 * Controle do movimento dos jogadores no teste de tiro
	 * @param game
	 */
    private void shotMode(Game game){
    	List<Player> playerList = game.getPlayerList();
    	while(playerList.size() > 1){
    		Player p = game.shot(getRandomPlayer(playerList));
    		if(p != null)
    			playerList.remove(p);
    	}
    }
    /**
     * Controle do movimento dos jogadores no teste de movimento
     * @param game
     */
    private void moveMode(Game game){
    	List<Player> playerList = game.getPlayerList();
    	while(playerList.size() > 0){
    		Player p = getRandomPlayer(playerList);
    		int x = new Random().nextInt(1024);
    		int y = new Random().nextInt(768);
    		System.out.println(String.format("Movimento Jogador=%s X=%s Y=%s", p.getNick(), x, y));
    		if(game.checkHitObstacle(p, x, y))
    			playerList.remove(p);
    	}
    }
    /**
     * Controle dos movimentos dos jogares no teste completo
     * @param game
     * @param tries
     */
    private void shotMoveShotMode(Game game, int tries){
    	List<Player> playerList = game.getPlayerList();
    	for(int i = 0; i <= tries; i++){
    		int x = new Random().nextInt(1024);
    		int y = new Random().nextInt(768);
    		
    		Player p = getRandomPlayer(playerList);
    		p.setPosX(x);
    		p.setPosY(y);
    		
    		game.shot(p);
    		game.checkHitObstacle(p,x,y );
    	}
    }
    /**
     * Retorna um jogador aleatório da lista
     * @param playerList
     * @return
     */
    private static Player getRandomPlayer(List<Player> playerList){
    	return playerList.get(new Random().nextInt(playerList.size()));
    }
	/**
	 * Método para atribuir uma arma dinamicamente
	 * @return Arma que será atribuida ao jogador
	 */
    private static Gun getRandom(){
    	List<Gun> gunList = createGunList();    	
    	return gunList.get(new Random().nextInt(gunList.size()));
    }
    /**
	 * Método para criar uma lista de armas
	 * @return Lista de armas a serem utilizadas
	 */
    private static List<Gun> createGunList(){
    	List<Gun> gunList = new ArrayList<Gun>();
    	Gun gun = new Gun();
		gun.setName("M16");		
		gunList.add(gun);
		
		gun = new Gun();
		gun.setName("M10");
		gunList.add(gun);
		
		gun = new Gun();
		gun.setName("M1");
		gunList.add(gun);
		
		gun = new Gun();
		gun.setName("M12");
		gunList.add(gun);
		
		gun = new Gun();
		gun.setName("16");
		gunList.add(gun);
		
		return gunList;
    }
    /**
	 * Método para criar uma lista de jogadores
	 * @return Lista de jogadores que irão interagir
	 */    
    private static List<Player> createPlayerList(){
    	List<Player> playerList = new ArrayList<Player>();
    	
    	Player p1 = new Player(new Random().nextInt(900), new Random().nextInt(700), ObjectType.PLAYER);
		p1.setNick("Teste 1");
		p1.setCurrentGun(getRandom());
		playerList.add(p1);
		
		Player p2 = new Player(new Random().nextInt(900), new Random().nextInt(700), ObjectType.PLAYER);
		p2.setNick("Teste 2");
		p2.setCurrentGun(getRandom());
		playerList.add(p2);
		
		Player p3 = new Player(new Random().nextInt(900), new Random().nextInt(700), ObjectType.PLAYER);
		p3.setNick("Teste 3");
		p3.setCurrentGun(getRandom());
		playerList.add(p3);
		
		Player p4 = new Player(new Random().nextInt(900), new Random().nextInt(700), ObjectType.PLAYER);
		p4.setNick("Teste 4");
		p4.setCurrentGun(getRandom());
		playerList.add(p4);
		
		Player p5 = new Player(new Random().nextInt(900), new Random().nextInt(700), ObjectType.PLAYER);
		p5.setNick("Teste 5");
		p5.setCurrentGun(getRandom());
		playerList.add(p5);
		
		Player p6 = new Player(new Random().nextInt(900), new Random().nextInt(700), ObjectType.PLAYER);
		p6.setNick("Teste 6");
		p6.setCurrentGun(getRandom());
		playerList.add(p6);
		
		Player p7 = new Player(new Random().nextInt(900), new Random().nextInt(700), ObjectType.PLAYER);
		p7.setNick("Teste 7");
		p7.setCurrentGun(getRandom());
		playerList.add(p7);
		
		Player p8 = new Player(new Random().nextInt(900), new Random().nextInt(700), ObjectType.PLAYER);
		p8.setNick("Teste 8");
		p8.setCurrentGun(getRandom());
		playerList.add(p8);
		
		return playerList;
    }


}