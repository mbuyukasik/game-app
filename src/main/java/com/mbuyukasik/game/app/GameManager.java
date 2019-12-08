package com.mbuyukasik.game.app;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbuyukasik.game.app.model.Match;
import com.mbuyukasik.game.app.model.Player;
import com.mbuyukasik.game.app.model.PlayerStatistics;
import com.mbuyukasik.game.app.repository.player.PlayerFileRepository;
import com.mbuyukasik.game.app.service.match.MatchService;
import com.mbuyukasik.game.app.service.output.OutputServiceFactory;
import com.mbuyukasik.game.app.service.player.PlayerService;
import com.mbuyukasik.game.app.service.rank.RankService;

/**
 * GameManager class keeps logic of all supported operations
 * For each operation, it prepares the data and delegates the output service to display it
 * 
 * @author: mehmet buyukasik
 * @version 1.0
 */
@Service
public class GameManager {

	private static Logger LOG = LoggerFactory.getLogger(PlayerFileRepository.class);

	private OutputServiceFactory outputServiceFactory;
	private PlayerService playerService;
	private MatchService matchService;
	private RankService rankService;
	
	public void setPlayerService(PlayerService playerService) {
		this.playerService = playerService;
	}

	public void setMatchService(MatchService matchService) {
		this.matchService = matchService;
	}

	@Autowired
	public GameManager(OutputServiceFactory outputServiceFactory, PlayerService playerService,
			MatchService matchService, RankService rankService) {
		this.outputServiceFactory = outputServiceFactory;
		this.playerService = playerService;
		this.matchService = matchService;
		this.rankService = rankService;
	}

	/**
	 * rankPlayers - This is a method to calculate player ranks considering matches they have played 
	 */
	@PostConstruct
	private void rankPlayers() {
		LOG.info("Calculating player statistics.");
		List<Match> matchList = this.matchService.list();
		List<Player> playerList = this.playerService.list();
		Map<Long, Player> playerMap = playerList.stream().collect(Collectors.toMap(Player::getId, player -> player));

		for (Match match : matchList) {
			Player playerA = playerMap.get(match.getPlayerA());
			if (playerA.getStatistics() == null) {
				playerA.setStatistics(new PlayerStatistics(playerA.getId(), 0, 0));
			}
			
			Player playerB = playerMap.get(match.getPlayerB());
			if (playerB.getStatistics() == null) {
				playerB.setStatistics(new PlayerStatistics(playerB.getId(), 0, 0));
			}
			
			rankService.calculateEloRating(playerA, playerB);
			playerA.getStatistics().setWinCount(playerA.getStatistics().getWinCount() + 1);
			playerB.getStatistics().setLooseCount(playerB.getStatistics().getLooseCount() + 1);
		}
		LOG.info("Player statistics calculation is completed.");
	}

	/**
	 * listPlayers - Outputs players in order
	 */
	public void listPlayers() {
		LOG.info("Listing player list.");
		this.outputServiceFactory.getOutputService().displayPlayers(this.preparePlayerList());
		LOG.info("Listing player list completed.");
	}
	
	/**
	 * listPlayerRanks - Outputs player list ordered with their rank (DESC)
	 */
	public void listPlayerRanks() {
		LOG.info("Listing player rank list.");
				this.outputServiceFactory.getOutputService().displayPlayerRanks(this.preparePlayerRanks());
		LOG.info("Listing player rank list completed.");
	}
	
	/**
	 * listPlayerMatches - Display players' previous matches
	 * @param playerId : This is an optional argument. If sent, only this player's matches are displayed
	 */
	public void listPlayerMatches(Long playerId) {
		LOG.info("Listing player's match list. playerId: " + playerId);
		this.outputServiceFactory.getOutputService().displayPlayerMatches(this.preparePlayerMatches(playerId));		
		LOG.info("Listing player's match list completed. playerId: " + playerId);
	}
	
	/**
	 * listSuggestedMatches - Outputs suggested match list
	 */
	public void listSuggestedMatches() {
		LOG.info("Listing suggested next matches");
		this.outputServiceFactory.getOutputService().displaySuggestedMatches(this.prepareSuggestedMatches());
		LOG.info("Listing suggested next matches completed");
	}

	/**
	 * 1 - Fetches all players
	 * 2 - Sort them with name field in ASC order
	 * 3 - Delegate outputservice to display the list
	 * 
	 * @return List<Player> ordered with name field
	 */
	public List<Player> preparePlayerList() {
		List<Player> playerList = this.playerService.list();

		// Compares players with name field in ASC order
		Comparator<Player> nameComparator = new Comparator<Player>() {

			@Override
			public int compare(Player player1, Player player2) {
				return player1.getName().compareTo(player2.getName());
			}

		};
		return playerList.stream().sorted(nameComparator).collect(Collectors.toList());
	}
	
	/**
	 * prepareListPlayerRanks
	 * 1 - Fetches all players
	 * 2 - Sort them with their rank in DESC order
	 * 3 - Delegate outputservice to display the list
	 * 
	 * @return List<Player> ordered with rank field DESC
	 */
	public List<Player> preparePlayerRanks() {
		List<Player> playerList = this.playerService.list();

		// Compares players with rank field in DESC order
		Comparator<Player> rankComparator = new Comparator<Player>() {

			@Override
			public int compare(Player player1, Player player2) {
				return Float.valueOf(player2.getRank()).compareTo(Float.valueOf(player1.getRank()));
			}

		};
		return playerList.stream().sorted(rankComparator).collect(Collectors.toList());
	}
	
	/**
	 * prepareListPlayerMatches - Display players' previous matches
	 * @param playerId : This is an optional argument. If sent, only this player's matches are displayed
	 */
	public List<Player> preparePlayerMatches(Long playerId) {
		List<Player> playerList = this.playerService.list();

		if (playerId != null) {
			playerList.clear();
			Player player = this.playerService.get(playerId);
			if (player != null) {
				playerList.add(player);
			} else {
				String message = "Player not found. playerId: " + playerId;
				LOG.info(message);
				System.out.println(message);
			}
		}
		
		return playerList;		
	}
	
	/**
	 * prepareSuggestedMatches - Prepares a match list suggestion
	 * Logic: Players whose previous match count is less than others are suggested to have the next match
	 */
	public List<Match> prepareSuggestedMatches() {
		List<Player> playerList = this.playerService.list();
		
		// Compares players with total match count in ASC order
		Comparator<Player> matchCountComparator = new Comparator<Player>() {
			@Override
			public int compare(Player player1, Player player2) {
				PlayerStatistics player1Statistics = player1.getStatistics();
				PlayerStatistics player2Statistics = player2.getStatistics();
				
				Float totalMatchCount1 = Float.valueOf(player1Statistics.getWinCount() + player1Statistics.getLooseCount());
				Float totalMatchCount2 = Float.valueOf(player2Statistics.getWinCount() + player2Statistics.getLooseCount());
				
				return totalMatchCount1.compareTo(totalMatchCount2);
			}
		};
		List<Player> sortedPlayerList = playerList.stream().sorted(matchCountComparator).collect(Collectors.toList());
		
		List<Match> suggestedMatchList = new ArrayList<Match>();
		for (int i = 0; i < sortedPlayerList.size(); i = i + 2) {
			Player player = sortedPlayerList.get(i);
			if ((i + 1) < sortedPlayerList.size()) {
				Player opponentPlayer = sortedPlayerList.get(i + 1);
				Match match = new Match(player.getId(), opponentPlayer.getId());
				suggestedMatchList.add(match);
			}
		}
		return suggestedMatchList;
	}
	
}
