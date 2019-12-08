package com.mbuyukasik.game.app.service.output;

import java.util.List;

import com.mbuyukasik.game.app.model.Match;
import com.mbuyukasik.game.app.model.Player;

/**
 * All functions that a standard output class should implement
 * 
 * @author: mehmet buyukasik
 * @version 1.0
 */
public interface IOutputService {

	void displayPlayers(List<Player> playerList);
	
	void displayPlayerRanks(List<Player> playerList);
	
	void displayPlayerMatches(List<Player> playerList);
	
	void displaySuggestedMatches(List<Match> matchList);
	
}
