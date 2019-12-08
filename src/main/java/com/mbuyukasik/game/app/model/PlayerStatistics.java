package com.mbuyukasik.game.app.model;

/**
 * Domain class to keep match statistics of a player
 * 
 * @author: mehmet buyukasik
 * @version 1.0
 */
public class PlayerStatistics {

	private Long playerId;
	private int winCount;
	private int looseCount;
	
	public PlayerStatistics(Long playerId, int winCount, int looseCount) {
		this.playerId = playerId;
		this.winCount = winCount;
		this.looseCount = looseCount;
	}
	
	public Long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}

	public int getWinCount() {
		return winCount;
	}

	public void setWinCount(int winCount) {
		this.winCount = winCount;
	}

	public int getLooseCount() {
		return looseCount;
	}

	public void setLooseCount(int looseCount) {
		this.looseCount = looseCount;
	}
	
}
