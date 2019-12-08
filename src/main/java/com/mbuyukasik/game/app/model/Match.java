package com.mbuyukasik.game.app.model;

/**
 * Domain class to keep player information and result of a match
 * 
 * @author: mehmet buyukasik
 * @version 1.0
 */
public class Match {

	private Long playerA;
	private Long playerB;
	private Long winnerPlayerId;

	public Match(long playerA, long playerB) {
		this(playerA, playerB, null);
	}

	public Match(long playerA, long playerB, Long winnerPlayerId) {
		this.playerA = playerA;
		this.playerB = playerB;
		this.winnerPlayerId = winnerPlayerId;
	}

	public Long getPlayerA() {
		return playerA;
	}

	public void setPlayerA(Long playerA) {
		this.playerA = playerA;
	}

	public Long getPlayerB() {
		return playerB;
	}

	public void setPlayerB(Long playerB) {
		this.playerB = playerB;
	}

	/**
	 * Checks if this is a match of user specified in playerId argument
	 * 
	 * @param playerId
	 * @return if user's match true, otherwise false
	 */
	public boolean isPlayersMatch(long playerId) {
		return (this.playerA != null && this.playerA.longValue() == playerId)
				|| (this.playerB != null && this.playerB.longValue() == playerId);
	}

	/**
	 * Checks if the user specified in playerId argument is the winner
	 * @param playerId
	 * @return true if player is winner, false otherwise
	 */
	public boolean isWinner(long playerId) {
		return this.winnerPlayerId != null && this.winnerPlayerId.longValue() == playerId;
	}
	
	@Override
	public boolean equals(Object objMatch) {
		boolean isEqual = false;
		if (objMatch != null && objMatch instanceof Match) {
			Match match = (Match) objMatch;
			if (match != null && this.playerA != null && match.getPlayerA()  != null && this.playerA.equals(match.getPlayerA())
					&& this.playerB != null && match.getPlayerB() != null && this.getPlayerB().equals(match.getPlayerB())) {
				isEqual = true;
			}
		}
		return isEqual;
	}

}
