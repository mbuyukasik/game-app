package com.mbuyukasik.game.app.service.output;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mbuyukasik.game.app.model.Match;
import com.mbuyukasik.game.app.model.Player;
import com.mbuyukasik.game.app.service.match.MatchService;
import com.mbuyukasik.game.app.service.player.PlayerService;

/**
 * Reports will be exported to excel files (Not implemented yet)
 * 
 * @author: mehmet buyukasik
 * @version 1.0
 */
@Service
public class ExcelOutputService implements IOutputService {

	private PlayerService playerService;
	private MatchService matchService;

	public ExcelOutputService(PlayerService playerService, MatchService matchService) {
		this.playerService = playerService;
		this.matchService = matchService;
	}

	@Override
	public void displayPlayers(List<Player> playerList) {
		// TODO: export to excel file
	}

	@Override
	public void displayPlayerRanks(List<Player> playerList) {
		// TODO: export to excel file
	}

	@Override
	public void displayPlayerMatches(List<Player> playerList) {
		// TODO: export to excel file
	}

	@Override
	public void displaySuggestedMatches(List<Match> matchList) {
		// TODO: export to excel file
	}

}
