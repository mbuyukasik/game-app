package com.mbuyukasik.game.app.repository.match;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mbuyukasik.game.app.GameAppTest;
import com.mbuyukasik.game.app.config.AppConfig;
import com.mbuyukasik.game.app.model.Match;
import com.mbuyukasik.game.app.model.Player;
import com.mbuyukasik.game.app.repository.player.PlayerFileRepository;
import com.mbuyukasik.game.app.util.FileUtil;

/**
 * MatchFileRepositoryTest is test class of MatchFileRepository
 * 
 * @author TTMBUYUKASIK
 * @version 1
 */
public class MatchFileRepositoryTest extends GameAppTest {

	@Autowired
	private MatchFileRepository matchFileRepository;

	@Autowired
	private PlayerFileRepository playerFileRepository;

	@Autowired
	private AppConfig appConfig;

	@Test
	public void listTest() {
		List<Match> matchList = matchFileRepository.list();

		String matchesFileName = appConfig.getConfigValue(AppConfig.PARAM_REPOSITORY_MATCH_FILE_NAME);

		List<String> expectedMatchFileLineList = FileUtil.readClassPathFile(matchesFileName);

		List<String> matchLineList = matchList.stream()
				.map(match -> String.format("%d %d", match.getPlayerA(), match.getPlayerB()))
				.collect(Collectors.toList());

		assertLinesMatch(expectedMatchFileLineList, matchLineList, "Match list is incorrect!");

	}

	@Test
	public void listPlayerMatchTest() {
		List<Player> playerList = playerFileRepository.list();
		List<Match> matchList = matchFileRepository.list();

		for (Player player : playerList) {
			List<Match> expectedPlayerMatchList = matchList.stream()
					.filter(match -> match.isPlayersMatch(player.getId())).collect(Collectors.toList());
			List<Match> playerMatchList = matchFileRepository.list(player.getId());

			assertTrue("Player match list size is incorrect! playerId: " + player.getId(),
					expectedPlayerMatchList.size() == playerMatchList.size());
		}

	}

}
