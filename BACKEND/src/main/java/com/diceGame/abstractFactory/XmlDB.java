package com.diceGame.abstractFactory;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import com.diceGame.utils.DBName;
import com.diceGame.utils.NbrTours;

public class XmlDB implements DBFactory {

	private String name;
	private static String FILE_NAME = "dice_game.xml";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private XmlDB() {
		this.name = DBName.MYSQL.getValue();
	}

	private static XmlDB INSTANCE = new XmlDB();

	public static XmlDB getInstance() {
		return INSTANCE;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> charger() {
		Map<String, HashMap<String, String>> map = null;
		// ouverture de decodeur
		XMLDecoder decoder;
		try {
			decoder = new XMLDecoder(new FileInputStream(FILE_NAME));
			// deserialisation de l'objet
			map = (Map<String, HashMap<String, String>>) decoder.readObject();
			decoder.close();
		} catch (FileNotFoundException e) {
			System.out.println("Fichier XML inexistant!");
			return null;
		}
		if (map != null && map.containsKey(String.valueOf(NbrTours.NOMBRE_MAX_TOURS)))
			return map.get(String.valueOf(NbrTours.NOMBRE_MAX_TOURS));
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void sauvegarder(int score, String pseudo) {

		Map<String, HashMap<String, String>> map = new HashMap<String, HashMap<String, String>>();
		XMLDecoder decoder;

		try {
			decoder = new XMLDecoder(new FileInputStream(FILE_NAME));
			// deserialisation de l'objet
			map = (Map<String, HashMap<String, String>>) decoder.readObject();
			decoder.close();

			if (map == null) {
				map = new HashMap<String, HashMap<String, String>>();
			}
		} catch (FileNotFoundException e1) {
			System.out.println(e1.getMessage());
		}
		Map<String, String> valeur = new HashMap<String, String>();

		valeur.put("score", String.valueOf(score));
		valeur.put("pseudo", pseudo);
		map.put(String.valueOf(NbrTours.NOMBRE_MAX_TOURS), (HashMap<String, String>) valeur);
		// ouverture de l'encodeur vers le fichier
		XMLEncoder encoder;
		try {
			encoder = new XMLEncoder(new FileOutputStream(FILE_NAME));
			encoder.writeObject(map);
			encoder.flush();
			encoder.close();
			System.out.println("Success");
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

}
