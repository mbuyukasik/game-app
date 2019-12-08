package com.mbuyukasik.game.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import com.mbuyukasik.game.app.model.Match;
import com.mbuyukasik.game.app.model.Player;
import com.mbuyukasik.game.app.service.match.MatchService;
import com.mbuyukasik.game.app.service.player.PlayerService;

/**
 * GameManagerTest - Test class of GameManager
 * 
 * @author TTMBUYUKASIK
 *
 */
public class GameManagerTest extends GameAppTest {

	private List<Player> playerList;
	private List<Match> matchList;

	@Mock
	private PlayerService playerService;

	@Mock
	private MatchService matchService;

	@Autowired
	private GameManager gameManager;

	/**
	 * Sets up the class
	 */
	@Before
	public void setUp() {
		// Initialize player list
		this.playerList = new ArrayList<Player>();
		this.matchList = new ArrayList<Match>();

		Player player1 = new Player(1l, "Mehmet", 2f);
		Player player2 = new Player(2l, "Defne", 1f);
		Player player3 = new Player(3l, "Guler", 3f);

		playerList.add(player1);
		playerList.add(player2);
		playerList.add(player3);

		// Initialize match map
		Match match1 = new Match(1l, 2l, 1l);
		Match match2 = new Match(2l, 1l, 2l);
		Match match3 = new Match(3l, 1l, 3l);
		Match match4 = new Match(3l, 2l, 3l);

		this.matchList.add(match1);
		this.matchList.add(match2);
		this.matchList.add(match3);
		this.matchList.add(match4);

		// Initialize mock methods
		Mockito.when(playerService.list()).thenReturn(playerList);
		Mockito.when(matchService.list(player1.getId())).thenReturn(this.matchList.stream()
				.filter(match -> match.isPlayersMatch(player1.getId())).collect(Collectors.toList()));
	}

	@Test
	public void preparePlayerListTest() {

		Mockito.when(playerService.list()).thenReturn(playerList);
		gameManager.setPlayerService(playerService);

		Comparator<Player> nameComparator = new Comparator<Player>() {

			@Override
			public int compare(Player player1, Player player2) {
				return player1.getName().compareTo(player2.getName());
			}

		};

		List<Player> expectedPlayerList = this.playerList.stream().sorted(nameComparator).collect(Collectors.toList());

		List<Player> genaratedPlayerList = gameManager.preparePlayerList();

		assertEquals(expectedPlayerList, genaratedPlayerList, "Player list genaration is incorrect!");

	}

	@Test
	public void preparePlayerRanksTest() {
		Mockito.when(playerService.list()).thenReturn(this.playerList);
		gameManager.setPlayerService(playerService);

		// Compares players with rank field in DESC order
		Comparator<Player> rankComparator = new Comparator<Player>() {

			@Override
			public int compare(Player player1, Player player2) {
				return Float.valueOf(player2.getRank()).compareTo(Float.valueOf(player1.getRank()));
			}

		};

		List<Player> expectedPlayerList = this.playerList.stream().sorted(rankComparator).collect(Collectors.toList());

		List<Player> genaratedPlayerList = gameManager.preparePlayerRanks();

		assertEquals(expectedPlayerList, genaratedPlayerList, "Player list genaration is incorrect!");
	}

	@Test
	public void preparePlayerMatchesTest() {

		gameManager.setPlayerService(playerService);

		List<Player> expectedPlayerList = this.playerList;

		List<Player> genaratedPlayerList = gameManager.preparePlayerMatches(null);

		assertEquals(expectedPlayerList, genaratedPlayerList,
				"Player list genaration is incorrect (preparePlayerMatchesTest)!");
	}

	@Test
	public void prepareSuggestedMatchesTest() {

		// Precalculated match list
		List<Match> expectedMatchList = new ArrayList<Match>();
		expectedMatchList.add(new Match(3, 1));

		List<Match> genaratedMatchList = this.gameManager.prepareSuggestedMatches();

		assertEquals(expectedMatchList, genaratedMatchList,
				String.format("Suggested match list incorrect! Expected: [playerA: %d, playerB: %d]",
						expectedMatchList.get(0).getPlayerA(), expectedMatchList.get(0).getPlayerB()));

	}

}
