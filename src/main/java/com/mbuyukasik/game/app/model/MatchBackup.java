package com.mbuyukasik.game.app.model;

/**
 * Domain class to keep player information and result of a match
 * 
 * @author: mehmet buyukasik
 * @version 1.0
 */
public class MatchBackup {

	private Long winnerPlayerId;
	private Long looserPlayerId;
	
	public MatchBackup(Long winnerPlayerId, Long looserPlayerId) {
		this.winnerPlayerId = winnerPlayerId;
		this.looserPlayerId = looserPlayerId;
	}

	public Long getWinnerPlayerId() {
		return winnerPlayerId;
	}

	public void setWinnerPlayerId(Long winnerPlayerId) {
		this.winnerPlayerId = winnerPlayerId;
	}

	public Long getLooserPlayerId() {
		return looserPlayerId;
	}

	public void setLooserPlayerId(Long looserPlayerId) {
		this.looserPlayerId = looserPlayerId;
	}
	
}
