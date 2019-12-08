package com.mbuyukasik.game.app.repository.player;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mbuyukasik.game.app.repository.EnmRepositoryType;
import com.mbuyukasik.game.app.repository.match.MatchRepoFactory;

/**
 * Factory class is responsible for providing the concrete player repository class
 * REPOSITORY_TYPE defines which class's instance will be returned
 * 
 * @author: mehmet buyukasik
 * @version 1.0
 */
@Service
public class PlayerRepoFactory {

	private static Logger LOG = LoggerFactory.getLogger(MatchRepoFactory.class);
	
	private PlayerFileRepository playerFileRepository;
	
	@Value("${REPOSITORY_TYPE}")
	private String paramRepositoryType;

	@Autowired
	public PlayerRepoFactory(PlayerFileRepository playerFileRepository) {
		this.playerFileRepository = playerFileRepository;
	}
	
	/**
	 * returns player repository object 
	 * REPOSITORY_TYPE defines which class's instance will be returned
	 * @return subClass object of IPlayerRepository interface
	 */
	public IPlayerRepository getPlayerRepository() {
		if (paramRepositoryType.equalsIgnoreCase(EnmRepositoryType.FILE.getId())) {
			return this.playerFileRepository;
		} else {
			LOG.error(String.format("Repository type not supported. (%s)", this.paramRepositoryType));
			return null;
		}
	}
	
}
