package dev.amil.cubo.framework;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.LinkedList;

/**
 * Classe pai dos objetos que podem ser inseridos no jogo
 * @author junior
 *
 */
public abstract class GameObject implements Serializable{
	
	private static final long serialVersionUID = -6743779053777238975L;
	protected float posX, posY;
	protected int width = 60, height = 60;	
	protected float speedPosX = 0, speedPosY = 0;
	protected ObjectType objType;
	
	public GameObject(float posX, float posY, ObjectType objectType){
		this.posX = posX;
		this.posY = posY;
		this.objType = objectType;
	}
	public abstract void tick(LinkedList<GameObject> objects);
	public abstract void render(Graphics g);
	public abstract Rectangle bounds();
	
	public float getPosX() {
		return this.posX;
	}
	public void setPosX(float posX) {
		this.posX = posX;
	}

	public float getPosY() {
		return this.posY;
	}

	public void setPosY(float posY) {
		this.posY = posY;
	}

	public float getSpeedPosX() {
		return this.speedPosX;
	}

	public void setSpeedPosX(float speedPosX) {
		this.speedPosX = speedPosX;
	}

	public float getSpeedPosY() {
		return this.speedPosY;
	}

	public void setSpeedPosY(float speedPosY) {
		this.speedPosY = speedPosY;
	}

	public ObjectType getObjectType() {
		return this.objType;
	}

	public void setObjectType(ObjectType objType) {
		this.objType = objType;
	}
	public int getWidth() {
		return this.width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight(){
		return this.height;
	}
	public void setHeight(int height){
		this.height = height;
	}
}
