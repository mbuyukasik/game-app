package com.mbuyukasik.game.app.repository.player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.mbuyukasik.game.app.model.Player;
import com.mbuyukasik.game.app.repository.BaseFileRepository;

/**
 * File system is the source of this player repository class. 
 * filePath and fileName values are read from application config
 * 
 * @author: mehmet buyukasik
 * @version 1.0
 */
@Repository
public class PlayerFileRepository extends BaseFileRepository<Player> implements IPlayerRepository {

	private static Logger LOG = LoggerFactory.getLogger(PlayerFileRepository.class);

	private Map<Long, Player> playerMap;

	@Value("${REPOSITORY_PLAYER_FILE_NAME}")
	private String paramPlayerFileName;

	/**
	 * Loads player list from file. Expected line format is: "playerId playerName". 
	 * Fields are space separated
	 */
	@PostConstruct
	public void loadPlayerFile() {
		playerMap = new HashMap<Long, Player>();
		List<String> strPlayerList = super.readFileRepository(this.paramPlayerFileName);
		if (strPlayerList != null && strPlayerList.size() > 0) {
			for (String line : strPlayerList) {
				String strPlayerId = line.substring(0, line.indexOf(" ")).trim();
				Long playerId = Long.parseLong(strPlayerId);
				String playerName = line.substring(line.indexOf(" ")).trim();
				Player player = new Player(playerId, playerName);
				playerMap.put(playerId, player);
			}
		} else {
			LOG.warn("PlayerFileRepository-loadPlayerFile - Player list is empty");
		}
	}

	@Override
	public List<Player> list() {
		return this.playerMap.values().stream().collect(Collectors.toList());
	}

	@Override
	public Player get(Long id) {
		return this.playerMap.get(id);
	}

}
