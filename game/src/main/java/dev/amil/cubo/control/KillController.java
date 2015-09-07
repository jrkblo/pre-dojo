package dev.amil.cubo.control;

import java.util.Calendar;

import dev.amil.cubo.framework.objects.Gun;
import dev.amil.cubo.framework.objects.Player;

public class KillController {
	private Player killer;
	private Player victim;
	private Gun gun;
	private Calendar killedTime;	
	
	public KillController(Player killer, Player victim, Gun gun, Calendar killedTime){
		this.killer = killer;
		this.victim = victim;
		this.gun = gun;
		this.killedTime = killedTime;
	}
	public Player getKiller() {
		return killer;
	}
	public void setKiller(Player killer) {
		this.killer = killer;
	}
	public Player getVictim() {
		return victim;
	}
	public void setVictim(Player death) {
		this.victim = death;
	}
	public Gun getGun() {
		return gun;
	}
	public void setGun(Gun gun) {
		this.gun = gun;
	}
	public Calendar getKilledTime() {
		return killedTime;
	}
	public void setKilledTime(Calendar killedTime) {
		this.killedTime = killedTime;
	}	
}
