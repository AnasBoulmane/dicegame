package com.diceGame.abstractFactory;

public class AbstractFactoryDB {

	public DBFactory getFactory(int type) {
		switch (type) {
		case 1:
			return MySqlDB.getInstance();
		case 2:
			return XmlDB.getInstance();
		default:
			return null;
		}
	}

}
