package dev.amil.cubo.framework.objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;

import dev.amil.cubo.framework.GameObject;
import dev.amil.cubo.framework.HandlerObject;
import dev.amil.cubo.framework.ObjectType;
/**
 * Classe para controle dos mapas disponiveis no jogo
 * Pode ser gerado vizualmente utilizando a classe render
 * O Mapa possui limites x e y, e podem ser inclusos diversos obstaculos (GameObject)
 * @author junior
 *
 */
public class Map  implements Serializable{	
	private static final long serialVersionUID = -6005895375542769946L;
	private List<GameObject> obstacleList;
	private int w, h;
	private String name;
	private int id;
	private HandlerObject handler;
	
	private Map(int w, int h){
		this.handler = new HandlerObject();
		render();
	}
	public static Map create(){
		Map m = new Map(1024, 768);
		
		return m;
	}
	private void render(){
		obstacleList = new ArrayList<GameObject>();		
		obstacleList.add(new River(0, 0, ObjectType.RIVER));
	}
	public List<GameObject> getObstacleList(){
		return ImmutableList.copyOf(obstacleList);
	}
	public int getW() {
		return w;
	}
	public void setW(int w) {
		this.w = w;
	}
	public int getH() {
		return h;
	}
	public void setH(int h) {
		this.h = h;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public HandlerObject getHandler() {
		return handler;
	}
	public void setHandler(HandlerObject handler) {
		this.handler = handler;
	}
	public void setObstacleList(List<GameObject> obstacleList) {
		this.obstacleList = obstacleList;
	}
}
