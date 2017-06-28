package com.diceGame.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.diceGame.abstractFactory.AbstractFactoryDB;
import com.diceGame.abstractFactory.DBFactory;
import com.diceGame.dto.Result;
import com.diceGame.model.Partie;
import com.diceGame.utils.NbrTours;

@RestController
@CrossOrigin
@RequestMapping("/game")
public class GameController {

	private static AbstractFactoryDB db = new AbstractFactoryDB();
	private static DBFactory dbFactoryDefault = db.getFactory(1);
	private static Partie partie = null;

	@GetMapping("/parametre")
	public Map<String, String> parametre(@RequestParam("nbrTour") int nbrTour, @RequestParam("stockage") int stockage) {
		Map<String, String> result = new HashMap<>();
		try {
			dbFactoryDefault = db.getFactory(stockage);
			NbrTours.NOMBRE_MAX_TOURS = nbrTour;
			result.put("message", "Success");
		} catch (Exception e) {
			result.put("message", e.getMessage());
		}
		return result;

	}

	@GetMapping("/login")
	public Map<String, String> authentification() {
		Map<String, String> result = new HashMap<>();
		try {
			partie = new Partie(dbFactoryDefault);
			result.put("message", "Success");
		} catch (Exception e) {
			result.put("message", e.getMessage());
		}
		return result;

	}

	@GetMapping("/meilleurScore")
	public Map<String, String> getMeilleurScore() {
		Map<String, String> stockage = new HashMap<String, String>();
		if (dbFactoryDefault.charger() != null) {
			stockage = dbFactoryDefault.charger();
			stockage.put("nbrTour", NbrTours.NOMBRE_MAX_TOURS + "");
			stockage.put("message", "Success");
		} else {
			stockage.put("message", "vide");
		}
		return stockage;

	}

	@GetMapping("/lancer")
	public Result lancer(@RequestParam("pseudo") String pseudo) {
		Result result = new Result();
		try {
			result = partie.lancer(pseudo);
		} catch (Exception e) {
			result = null;
			System.out.println(e.getMessage());
		}
		return result;
	}

}
