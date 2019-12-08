package com.mbuyukasik.game.app.service.output;

import java.util.List;
import java.util.stream.IntStream;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.mbuyukasik.game.app.model.Match;
import com.mbuyukasik.game.app.model.Player;
import com.mbuyukasik.game.app.service.match.MatchService;
import com.mbuyukasik.game.app.service.player.PlayerService;

/**
 * ConsoleOutputService is customized to display reports on console No business
 * logic is applied on parameters/lists. They are just formatted and displayed
 * 
 * @author: mehmet buyukasik
 * @version 1.0
 */
@Service
public class ConsoleOutputService implements IOutputService {

	private PlayerService playerService;
	private MatchService matchService;

	public ConsoleOutputService(PlayerService playerService, MatchService matchService) {
		this.playerService = playerService;
		this.matchService = matchService;
	}

	/**
	 * displayPlayers - Displays id and name of players in playList parameter
	 * 
	 * @param - playerList : player list to be displayed
	 */
	@Override
	public void displayPlayers(List<Player> playerList) {
		System.out.println(String.format("%-5s	%-40s", "Id", "Player Name"));
		System.out.println(String.format("%-5s	%-40s", StringUtils.repeat("-", 5), StringUtils.repeat("-", 20)));
		playerList.stream()
				.forEach(player -> System.out.println(String.format("%-5d	%-40s", player.getId(), player.getName())));
	}

	/**
	 * displayPlayerRanks - Displays players ordered with their rank
	 * 
	 * @param - playerList : player list to be displayed
	 */
	@Override
	public void displayPlayerRanks(final List<Player> playerList) {
		System.out.println(String.format("%-5s	%-25s	%-10s	%-15s	%-15s", "Id", "Player Name", "Rank",
				"Win Count", "Loose Count"));
		System.out.println(String.format("%-5s	%-25s	%-10s	%-15s	%-15s", StringUtils.repeat("-", 5),
				StringUtils.repeat("-", 15), StringUtils.repeat("-", 10), StringUtils.repeat("-", 15),
				StringUtils.repeat("-", 15)));
		IntStream.range(0, playerList.size()).forEach(
				i -> System.out.println(String.format("%-5d	%-25s	%-10d	%-15s	%-15s", playerList.get(i).getId(),
						playerList.get(i).getName(), i + 1, playerList.get(i).getStatistics().getWinCount(),
						playerList.get(i).getStatistics().getLooseCount())));
	}

	/**
	 * displayPlayerMatches - Displays matches of each player in the playerList, and
	 * grouped by player name
	 * 
	 * @param - playerList : Each player's match list will be displayed under name
	 *          of player
	 */
	@Override
	public void displayPlayerMatches(List<Player> playerList) {
		for (Player player : playerList) {
			System.out.println(String.format(player.getName().toUpperCase() + " played with: "));
			System.out.println(String.format("%40s", StringUtils.repeat("-", 40)));

			List<Match> playerMatchList = this.matchService.list(player.getId());

			if (playerMatchList != null && playerMatchList.size() > 0) {
				for (Match match : playerMatchList) {
					Long opponentPlayerId = match.getPlayerA().equals(player.getId()) ? match.getPlayerB()
							: match.getPlayerA();
					Player opponentPlayer = this.playerService.get(opponentPlayerId);
					String result = match.isWinner(player.getId()) ? "WON" : "LOST";
					System.out.println(String.format("%-30s	%-10s", opponentPlayer.getName(), result));
				}
			}

			System.out.println();
			System.out.println(StringUtils.repeat("*", 50));
			System.out.println();
		}
	}

	/**
	 * displaySuggestedMatches - Displays playerA and playerB information of
	 * suggested matches
	 * 
	 * @param - matchList : match list to be displayed
	 */
	@Override
	public void displaySuggestedMatches(List<Match> matchList) {
		System.out.println(String.format("%-20s	%-20s", "Player A", "Player B"));
		System.out.println(String.format("%-20s	%-20s", StringUtils.repeat("-", 20), StringUtils.repeat("-", 20)));
		for (Match match : matchList) {
			Player playerA = this.playerService.get(match.getPlayerA());
			Player playerB = this.playerService.get(match.getPlayerB());

			System.out.println(String.format("%-20s	%-20s", playerA.getName(), playerB.getName()));
		}
	}

}
