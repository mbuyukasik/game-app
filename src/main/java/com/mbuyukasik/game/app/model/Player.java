
package com.mbuyukasik.game.app.model;

/**
 * Domain class to keep information of a player
 * 
 * @author: mehmet buyukasik
 * @version 1.0
 */
public class Player {

	private Long id;
	private String name;
	private float rank;
	private PlayerStatistics statistics;

	public Player(Long id, String name) {
		this(id, name, 0f);
	}
	
	public Player(Long id, String name, float rank) {
		this(id, name, rank, null);
	}

	public Player(Long id, String name, Float rank, PlayerStatistics statistics) {
		this.id = id;
		this.name = name;
		this.rank = rank;
		this.statistics = statistics;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getRank() {
		return rank;
	}

	public void setRank(float rank) {
		this.rank = rank;
	}

	public PlayerStatistics getStatistics() {
		return statistics;
	}

	public void setStatistics(PlayerStatistics statistics) {
		this.statistics = statistics;
	}

	@Override
	public boolean equals(Object objPlayerB) {
		boolean isEqual = false;
		if (objPlayerB != null && objPlayerB instanceof Player) {
			Player playerB = (Player) objPlayerB;
			if (playerB != null && this.id != null && playerB.getId() != null && this.id.equals(playerB.getId())
					&& this.name != null && playerB.getName() != null && this.name.equals(playerB.getName())) {
				isEqual = true;
			}
		}
		return isEqual;
	}

}
