package com.mbuyukasik.game.app.repository.player;

import static org.junit.jupiter.api.Assertions.assertLinesMatch;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mbuyukasik.game.app.GameAppTest;
import com.mbuyukasik.game.app.config.AppConfig;
import com.mbuyukasik.game.app.model.Player;
import com.mbuyukasik.game.app.util.FileUtil;

/**
 * PlayerFileRepositoryTest is test class of PlayerFileRepository
 * 
 * @author TTMBUYUKASIK
 * @version 1
 */
public class PlayerFileRepositoryTest extends GameAppTest {

	@Autowired
	private PlayerFileRepository playerFileRepository;

	@Autowired
	private AppConfig appConfig;

	@Test
	public void listTest() {
		List<Player> playerList = playerFileRepository.list();

		String playerFileName = appConfig.getConfigValue(AppConfig.PARAM_REPOSITORY_PLAYER_FILE_NAME);

		List<String> expectedPlayerFileLineList = FileUtil.readClassPathFile(playerFileName);

		List<String> playerLineList = playerList.stream()
				.map(player -> String.format("%d %s", player.getId(), player.getName()))
				.collect(Collectors.toList());

		assertLinesMatch(expectedPlayerFileLineList, playerLineList, "Player list is incorrect!");

	}

}
