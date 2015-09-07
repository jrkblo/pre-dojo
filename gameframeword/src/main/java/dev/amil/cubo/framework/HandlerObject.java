package dev.amil.cubo.framework;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Optional;

/**
 * Classe que acopla a inclusão de novos objetos no jogo
 * @author junior
 *
 */
public class HandlerObject  implements Serializable{
	
	private static final long serialVersionUID = 593591651074938886L;
	private LinkedList<GameObject> objectList = new LinkedList<GameObject>();
	private GameObject tempObject;
	
	public void tick(){
		objectList.stream().forEach(o -> {
			tempObject = o;
			tempObject.tick(objectList);
		});
	}
	public void render(){
		
	}
	public void removeGameObject(GameObject gameObject){
		objectList.remove(gameObject);
	}
	public void addGameObject(GameObject gameObject){
		checkNotNull(gameObject);
		Optional<GameObject> repeated = objectList.stream().filter(i -> i.getObjectType() == gameObject.getObjectType()).findAny();
		
		objectList.add(repeated.map(i -> {
			objectList.remove(i);
			return (GameObject) getGameObject(gameObject);			
		}).orElse(gameObject));
	}
	private <T> T getGameObject(GameObject gameObject){
		return (T) gameObject;
	}
}
