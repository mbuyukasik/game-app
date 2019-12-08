package com.mbuyukasik.game.app.repository.match;

import java.util.List;

import com.mbuyukasik.game.app.model.Match;

/**
 * Common methods of match repository
 * 
 * @author: mehmet buyukasik
 * @version 1.0
 */
public interface IMatchRepository {
	
	public List<Match> list();
	
	public List<Match> list(Long playerId);
	
}
