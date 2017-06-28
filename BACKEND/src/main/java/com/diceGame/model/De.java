package com.diceGame.model;

import java.util.Observable;
import java.util.Random;

public class De extends Observable {

	private int etat;
	private int id;

	public De() {
	}

	public De(int id) {
		this.setId(id);
	}

	public void roll() {
		Random rand = new Random();
		// Random entre 1 et 6
		setEtat(rand.nextInt(6) + 1);
		// On notifie l'observateur
		this.setChanged();
		this.notifyObservers(id);
	}

	public int getEtat() {
		return etat;
	}

	public void setEtat(int etat) {
		this.etat = etat;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
