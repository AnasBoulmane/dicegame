package com.diceGame.dto;

import com.diceGame.model.De;

public class Result {

	private De de1;
	private De de2;
	private int score;
	private int meilleurScore;
	private String pseudoMeilleurScore;

	public String getPseudoMeilleurScore() {
		return pseudoMeilleurScore;
	}

	public void setPseudoMeilleurScore(String pseudoMeilleurScore) {
		this.pseudoMeilleurScore = pseudoMeilleurScore;
	}

	public int getMeilleurScore() {
		return meilleurScore;
	}

	public void setMeilleurScore(int meilleurScore) {
		this.meilleurScore = meilleurScore;
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

	public Result(De de1, De de2, int score) {
		super();
		this.de1 = de1;
		this.de2 = de2;
		this.score = score;
	}

	public Result() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Result [de1=" + de1.getEtat() + ", de2=" + de2.getEtat() + ", score=" + score + "]";
	}

}
