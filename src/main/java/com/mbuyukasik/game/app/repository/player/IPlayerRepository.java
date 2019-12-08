package com.mbuyukasik.game.app.repository.player;

import java.util.List;

import com.mbuyukasik.game.app.model.Player;

/**
 * Common methods of player repository
 * 
 * @author: mehmet buyukasik
 * @version 1.0
 */
public interface IPlayerRepository {
	
	public List<Player> list();
	
	public Player get(Long id);
	
}
