package com.mbuyukasik.game.app.service.match;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbuyukasik.game.app.model.Match;
import com.mbuyukasik.game.app.repository.match.MatchRepoFactory;
import com.mbuyukasik.game.app.repository.match.IMatchRepository;
import com.mbuyukasik.game.app.service.IService;

/**
 * MatchService is used as a proxy in front of repository class
 * Also used to keep business logic about Match entity
 * 
 * @author: mehmet buyukasik
 * @version 1.0
 */
@Service
public class MatchService implements IService<Match> {
	
	private IMatchRepository matchRepository;
	
	@Autowired
	public MatchService(MatchRepoFactory matchRepoFactory) {
		this.matchRepository = matchRepoFactory.getMatchRepository();
	}
	
	public List<Match> list() {
		return this.matchRepository.list();
	}
	
	public List<Match> list(Long playerId) {
		return this.matchRepository.list(playerId);
	}
	
}
