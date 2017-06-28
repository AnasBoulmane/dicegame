package com.diceGame.abstractFactory;

import java.util.Map;

public interface DBFactory {

	public Map<String, String> charger();

	public void sauvegarder(int score, String pseudo);
}
