package com.mbuyukasik.game.app.repository.match;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mbuyukasik.game.app.repository.EnmRepositoryType;

/**
 * Factory class is responsible for providing the concrete match repository class
 * REPOSITORY_TYPE defines which class's instance will be returned
 * 
 * @author: mehmet buyukasik
 * @version 1.0
 */
@Service
public class MatchRepoFactory {

	private static Logger LOG = LoggerFactory.getLogger(MatchRepoFactory.class);
	
	private MatchFileRepository matchFileRepository;
	
	@Value("${REPOSITORY_TYPE}")
	private String paramRepositoryType;
	
	@Autowired
	public MatchRepoFactory(MatchFileRepository matchFileRepository) {
		this.matchFileRepository = matchFileRepository;
	}
	
	/**
	 * returns match repository object 
	 * REPOSITORY_TYPE defines which class's instance will be returned
	 * @return subClass object of IMatchRepository interface
	 */
	public IMatchRepository getMatchRepository() {
		if (paramRepositoryType.equalsIgnoreCase(EnmRepositoryType.FILE.getId())) {
			return this.matchFileRepository;
		} else {
			LOG.error(String.format("Repository type not supported. (%s)", this.paramRepositoryType));
			return null;
		}
	}
	
}
