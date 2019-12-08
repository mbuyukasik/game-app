package com.mbuyukasik.game.app.repository;

/**
 * @author: mehmet buyukasik
 * @version 1.0
 */
public enum EnmRepositoryType {

	FILE("FILE"), DATABASE("DATABASE");

	private String id;

	EnmRepositoryType(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}
}
