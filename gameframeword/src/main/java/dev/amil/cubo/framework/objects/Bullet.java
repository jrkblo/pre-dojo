package dev.amil.cubo.framework.objects;

import java.io.Serializable;

/**
 * Classe que controle as balas que podem ser utilizadas nas armas
 * Pode-se implementar velocidade, alcance, dano
 * @author junior
 *
 */
public class Bullet implements Serializable{
	private static final long serialVersionUID = -5056024272594408501L;
	private Direction direction;
    private float speed = 1.2f;
    private int x;
    private int y;

    public Bullet(int x, int y){
         this.x =x;
         this.y=y;        
    }

    public void launchBullet(){
        
    }

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}    
}
