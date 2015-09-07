package dev.amil.cubo.framework.objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;

/**
 * Classe que representa as armas dispiniveis no jogo
 * @author junior
 *
 */
public class Gun implements Serializable {	
	private static final long serialVersionUID = 4697652591209226046L;
	private List<Bullet> firedBullets = new ArrayList<Bullet>();
	private String name;	

	public List<Bullet> getFiredBullets() {
		return ImmutableList.copyOf(firedBullets);
	}

	public void removerFiredBullet(Bullet bullet) {
		firedBullets.remove(bullet);
	}

	public void fire(int x, int y) {
		Bullet bullet = new Bullet(x, y);
		firedBullets.add(bullet);
		bullet.launchBullet();
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
}
