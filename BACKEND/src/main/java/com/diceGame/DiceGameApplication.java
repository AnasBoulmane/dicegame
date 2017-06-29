package com.diceGame;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DiceGameApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DiceGameApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {

		//AbstractFactoryDB db = new AbstractFactoryDB();

		/** test DataBase MySql **/
		//DBFactory dbFactoryMySql = db.getFactory(1);
		// dbFactoryMySql.sauvegarder(20, "simo");

		/** test DataBase XML **/
		// DBFactory dbFactoryXml = db.getFactory(2);
		// dbFactoryXml.charger(10);
		// dbFactoryXml.sauvegarder(20, "simo");

		/** test Changer Paramètre **/
		//NbrTours.NOMBRE_MAX_TOURS = 11;

		/** test Meilleur Score **/
		/*Map<String, String> stockage = dbFactoryMySql.charger();
		if (stockage != null) {
			String pseudo = dbFactoryMySql.charger().get("pseudo");
			String score = dbFactoryMySql.charger().get("score");
			System.out.println("*********************************");
			System.out.println("Pseudo : " + pseudo + " - Score : " + score);
			System.out.println("*********************************");
		}*/
		/** test Partie Lancer **/
		/*Partie partie = new Partie(dbFactoryMySql);
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

		}*/

	}
}
