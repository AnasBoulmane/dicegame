package com.diceGame;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.diceGame.abstractFactory.AbstractFactoryDB;
import com.diceGame.abstractFactory.DBFactory;
import com.diceGame.dto.Result;
import com.diceGame.model.Partie;
import com.diceGame.utils.NbrTours;

@SpringBootApplication
public class DiceGameApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DiceGameApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {

		AbstractFactoryDB db = new AbstractFactoryDB();

		/** test DataBase MySql **/
		DBFactory dbFactoryMySql = db.getFactory(2);
		// dbFactoryMySql.sauvegarder(20, "simo");

		/** test DataBase XML **/
		// DBFactory dbFactoryXml = db.getFactory(2);
		// dbFactoryXml.charger(10);
		// dbFactoryXml.sauvegarder(20, "simo");

		/** test Changer Paramètre **/
		NbrTours.NOMBRE_MAX_TOURS = 11;

		String pseudo = dbFactoryMySql.charger().get("pseudo");
		String score = dbFactoryMySql.charger().get("score");
		System.out.println("*********************************");
		System.out.println("Pseudo : " + pseudo + " - Score : " + score);
		System.out.println("*********************************");

		/** test Partie Lancer **/
		Partie partie = new Partie(dbFactoryMySql);
		for (int i = 0; i < NbrTours.NOMBRE_MAX_TOURS; i++) {
			System.out.println("------------------" + (i + 1) + "-------------------");
			Result result = partie.lancer("testPartie");
			System.out.println("De 1 : " + result.getDe1().getEtat());
			System.out.println("De 2 : " + result.getDe2().getEtat());
			int res = result.getDe1().getEtat() + result.getDe2().getEtat();
			System.out.println("Resultat obtenu : " + res);
			if (res == 7)
				System.out.println("Bien Joué :)");
			else
				System.out.println("Aucune chance :(");
			System.out.println("----------------------------------------------------");
			if ((i + 1) == NbrTours.NOMBRE_MAX_TOURS) {
				System.out.println("C fini Mercii ");
				if (result.getMeilleurScore() <= result.getScore()) {
					System.out.println("*******************************");
					System.out.println("**");
					System.out.println("**    Vous avez Gagner :)");
					System.out.println("**    Pseudo : " + result.getPseudoMeilleurScore());
					System.out.println("**    Score : " + result.getMeilleurScore());
					System.out.println("**");
					System.out.println("*******************************");
				} else {
					System.out.println("*******************************");
					System.out.println("**");
					System.out.println("**    Vous avez Perdu :( ");
					System.out.println("**    Pseudo : " + result.getPseudoMeilleurScore());
					System.out.println("**    Score : " + result.getMeilleurScore());
					System.out.println("**");
					System.out.println("*******************************");
				}
			}

		}

	}
}
