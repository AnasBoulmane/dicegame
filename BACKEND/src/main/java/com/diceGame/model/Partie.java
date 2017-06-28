package com.diceGame.model;

import java.util.Map;
import java.util.Observable;

import com.diceGame.abstractFactory.DBFactory;
import com.diceGame.dto.Result;
import com.diceGame.utils.NbrTours;

public class Partie extends Observable {

	private int tour;
	private int score;
	private De de1;
	private De de2;
	private DBFactory meilleurScore;

	public Partie(DBFactory meilleurScore) {
		this.tour = 0;
		this.de1 = new De(1);
		this.de2 = new De(2);
		this.score = 0;
		this.meilleurScore = meilleurScore;
	}

	public Result lancer(String nomJoueur) {
		Integer arg = null;
		int meilleurScore = -1;
		String pseudoMeilleurScore = "";
		de1.roll();
		de2.roll();
		tour += 1;

		// Score incr�ment� de 10 points si le total des d�s fait 7
		if (de1.getEtat() + de2.getEtat() == 7)
			score += 10;

		// Si c'est la fin du jeu, on sauvegarde le score si c'est le meilleur
		// score
		if (NbrTours.NOMBRE_MAX_TOURS == tour) {

			Map<String, String> highscore = getMeilleurScore().charger();
			// sauvegarder(score)
			if (highscore == null || highscore.isEmpty()
					|| Integer.parseInt((String) highscore.get("score")) <= score) {
				arg = score;
				getMeilleurScore().sauvegarder(score, nomJoueur);
				Map<String, String> highscore2 = getMeilleurScore().charger();
				meilleurScore = converterStringToInteger(highscore2.get("score"));
				pseudoMeilleurScore = highscore2.get("pseudo");
				System.out.println("----------------- GANGER -------------------");
			}else{
				meilleurScore = converterStringToInteger(highscore.get("score"));
				pseudoMeilleurScore = highscore.get("pseudo");
				System.out.println("----------------- PERDU -------------------");
			}

		}
		Result result = new Result(de1, de2, score);
		if (NbrTours.NOMBRE_MAX_TOURS == tour) {
			result.setMeilleurScore(meilleurScore);
			result.setPseudoMeilleurScore(pseudoMeilleurScore);
		}
		// On notifie les observateurs
		this.setChanged();
		this.notifyObservers(arg);
		return result;
	}

	private int converterStringToInteger(String value) {
		return Integer.valueOf(value);
	}

	public int getTour() {
		return tour;
	}

	public void setTour(int tour) {
		this.tour = tour;
	}

	public De getDe1() {
		return de1;
	}

	public void setDe1(De de1) {
		this.de1 = de1;
	}

	public De getDe2() {
		return de2;
	}

	public void setDe2(De de2) {
		this.de2 = de2;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public DBFactory getMeilleurScore() {
		return meilleurScore;
	}

	public void setMeilleurScore(DBFactory meilleurScore) {
		this.meilleurScore = meilleurScore;
	}
}
