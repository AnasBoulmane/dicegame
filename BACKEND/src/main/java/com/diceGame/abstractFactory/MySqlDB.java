package com.diceGame.abstractFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.diceGame.utils.DBName;
import com.diceGame.utils.NbrTours;

public class MySqlDB implements DBFactory {

	private String name;
	private static String DRIVER = "com.mysql.jdbc.Driver";
	private static String URL = "jdbc:mysql://localhost:3306/";
	private static String USER = "root";
	private static String PASSWORD = "root";
	private static String DB_NAME = "dice_game";
	private static String TABLE_NAME = "meilleur_score";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private MySqlDB() {
		this.name = DBName.MYSQL.getValue();
	}

	private static MySqlDB INSTANCE = new MySqlDB();

	public static MySqlDB getInstance() {
		return INSTANCE;
	}

	private void connexion() {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println("Erreur lors du chargement : le driver n'a pas été trouvé dans le classpath ! <br/>"
					+ e.getMessage());
		}
	}

	@Override
	public Map<String, String> charger() {
		Map<String, String> result = null;
		/* Chargement du driver JDBC pour MySQL */
		this.connexion();

		/* Connexion à la base de données */
		Connection connexion = null;
		try {
			String url = URL + "?verifyServerCertificate=false&useSSL=false&requireSSL=false";
			connexion = DriverManager.getConnection(url, USER, PASSWORD);
			Statement s = connexion.createStatement();
			s.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DB_NAME);
			s.executeUpdate("use " + DB_NAME);

			String sql = "CREATE TABLE IF NOT EXISTS `" + TABLE_NAME + "` (" + "`nombre_tour` int(11) NOT NULL,"
					+ "`score` int(11) NOT NULL," + "`pseudo` varchar(255) NOT NULL," + " PRIMARY KEY (`nombre_tour`)"
					+ ") ENGINE=InnoDB DEFAULT CHARSET=latin1;";

			s.executeUpdate(sql);

			String selectTableSQL = "SELECT score, pseudo from " + TABLE_NAME + " WHERE nombre_tour="
					+ NbrTours.NOMBRE_MAX_TOURS;
			s = connexion.createStatement();
			ResultSet rs = s.executeQuery(selectTableSQL);
			while (rs.next()) {
				String score = rs.getString("score");
				String pseudo = rs.getString("pseudo");
				if (score != null && pseudo != null) {
					result = new HashMap<String, String>();
					result.put("score", String.valueOf(score));
					result.put("pseudo", pseudo);
				}

			}

		} catch (SQLException e) {
			System.out.println(e);
			/* Gérer les éventuelles erreurs ici */
			System.out.println("Exception.............. !");
		} finally {
			if (connexion != null)
				try {
					/* Fermeture de la connexion */
					connexion.close();
				} catch (SQLException ignore) {
					/*
					 * Si une erreur survient lors de la fermeture, il suffit de
					 * l'ignorer.
					 */
				}
		}

		return result;
	}

	@SuppressWarnings("unused")
	private boolean isExit(int tour) {
		this.connexion();
		/* Connexion à la base de données */
		Connection connexion = null;
		try {
			String url = URL + "?verifyServerCertificate=false&useSSL=false&requireSSL=false";
			connexion = DriverManager.getConnection(url, USER, PASSWORD);
			Statement s = connexion.createStatement();
			s.executeUpdate("use " + DB_NAME);
			String selectTableSQL = "SELECT nombre_tour from " + TABLE_NAME + " WHERE nombre_tour=" + tour;
			s = connexion.createStatement();
			ResultSet rs = s.executeQuery(selectTableSQL);
			String nbrTour = "";
			while (rs.next()) {
				nbrTour = rs.getString("nombre_tour");
			}
			if (connexion != null)
				connexion.close();
			if (nbrTour.equals("") || nbrTour == null)
				return false;
			else
				return true;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			/* Gérer les éventuelles erreurs ici */
			System.out.println("Exception.............. !");
			return false;
		}
	}

	@Override
	public void sauvegarder(int score, String pseudo) {
		this.connexion();

		Connection connexion = null;
		try {
			String url = URL + "?verifyServerCertificate=false&useSSL=false&requireSSL=false";
			connexion = DriverManager.getConnection(url, USER, PASSWORD);
			Statement s = connexion.createStatement();
			s.executeUpdate("use " + DB_NAME);
			String rqtSQL = "";
			boolean exist = this.isExit(NbrTours.NOMBRE_MAX_TOURS);
			if (exist) {
				rqtSQL = "UPDATE " + TABLE_NAME + " SET score=" + score + ", pseudo='" + pseudo + "' WHERE nombre_tour="
						+ NbrTours.NOMBRE_MAX_TOURS;
			} else {
				rqtSQL = "INSERT INTO " + TABLE_NAME + "(nombre_tour, score, pseudo) VALUES" + "("
						+ NbrTours.NOMBRE_MAX_TOURS + "," + score + ",'" + pseudo + "')";
			}
			PreparedStatement preparedStatement = connexion.prepareStatement(rqtSQL);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			/* Gérer les éventuelles erreurs ici */
			System.out.println("Exception.............. !");
		} finally {
			if (connexion != null)
				try {
					/* Fermeture de la connexion */
					connexion.close();
				} catch (SQLException ignore) {
					/*
					 * Si une erreur survient lors de la fermeture, il suffit de
					 * l'ignorer.
					 */
				}
		}
	}

}
