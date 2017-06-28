package com.diceGame.utils;

public enum DBName {

	MYSQL("MySql"), XML("Xml");

	private final String value;

	private DBName(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

}
