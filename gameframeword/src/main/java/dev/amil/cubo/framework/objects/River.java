package dev.amil.cubo.framework.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.LinkedList;

import dev.amil.cubo.framework.GameObject;
import dev.amil.cubo.framework.ObjectType;

/**
 * Objeto do tipo obstaculo que herda da classe GameObject, que contém todas as informações básicas que cada objeto do jogo deve possuir
 * @author junior
 *
 */
public class River extends GameObject implements Serializable{

	private static final long serialVersionUID = -3044131864728157134L;

	public River(float posX, float posY, ObjectType objectType) {
		super(posX, posY, objectType);
		width = 200;
		height = 400;
	}	
	public void tick(LinkedList<GameObject> objects) {
			
	}

	public void render(Graphics g) {
			
	}

	public Rectangle bounds() {
		return new Rectangle(10, 0 , width, height);
	}

}
