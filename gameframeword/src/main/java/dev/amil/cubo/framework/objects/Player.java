package dev.amil.cubo.framework.objects;

import static com.google.common.base.Preconditions.checkNotNull;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import com.google.common.collect.ImmutableList;

import dev.amil.cubo.framework.GameObject;
import dev.amil.cubo.framework.ObjectType;

/**
 * Classe que representa os jogadores
 * 
 * @author junior
 *
 */
public class Player extends GameObject implements Serializable{	
	private static final long serialVersionUID = 6872503509166692896L;
	private String nick;
	private boolean alive = true;
	private Gun currentGun;
	private List<Gun> arsenal = new ArrayList<Gun>();
	
	public Player(float posX, float posY, ObjectType objectType) {
		super(posX, posY, objectType);
	}
	public String getNick(){
		return this.nick;
	}
	public void setNick(String nick){
		this.nick = nick;
	}
	public boolean isAlive(){
		return this.alive;
	}
	public void setAlive(boolean isAlive){
		this.alive = isAlive;
	}
	/**
	 * Retorna um cópia da lista de armas do arsenal 
	 * */
	public List<Gun> getArsenal() {
		return ImmutableList.copyOf(arsenal);
	}
	/**
	 * Inclui uma nova arma ao arsenal
	 * Caso o jogador já possua a mesma arma, a nova arma é ignorada
	 * */
	public void addGunToArsenal(Gun gun) {
		checkNotNull(gun);
		Optional<Gun> repeated = arsenal.stream().filter(i -> i.getName().equals(currentGun.getName())).findAny();
		arsenal.add(repeated.map(i -> {
			arsenal.remove(i);
			return repeated.get();			
		}).orElse(gun));
		setCurrentGun(gun);
	}
	
	public Gun getCurrentGun() {
		return currentGun;
	}	
	/**
	 * Definir a arma atual do jogador
	 * Caso seja uma arma que foi adquirida no mesmo momento de ser definida como atual
	 * é verificado se a mesma já existe no arsenal, caso exista, a mesma é ignorada
	 * */
	public void setCurrentGun(Gun currentGun) {
		checkNotNull(currentGun);
		Optional<Gun> repeated = arsenal.stream().filter(i -> i.getName().equals(currentGun.getName())).findAny();
		arsenal.add(repeated.map(i -> {
			arsenal.remove(i);
			Gun g = new Gun();
			g.setName(i.getName());
			return g;
		}).orElse(currentGun));
		
		if(repeated != null)
			this.currentGun = currentGun;
	}
	
	public void tick(LinkedList<GameObject> objects) {		
	}	
	public void render(Graphics g) {			
	}	
	public Rectangle bounds() {		
		return new Rectangle((int)posX, (int)posY, width, height);
	}
	public void shot(){
		currentGun.fire((int)posX, (int)posY);		
	}
}
