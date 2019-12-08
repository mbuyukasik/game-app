package com.mbuyukasik.game.app.repository.match;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.mbuyukasik.game.app.model.Match;
import com.mbuyukasik.game.app.repository.BaseFileRepository;

/**
 * File system is the source of this match repository class. 
 * filePath and fileName values are read from application config
 * 
 * @author: mehmet buyukasik
 * @version 1.0
 */
@Repository
public class MatchFileRepository extends BaseFileRepository<Match> implements IMatchRepository {

	private static Logger LOG = LoggerFactory.getLogger(MatchFileRepository.class);

	private List<Match> matchList;
	private Map<Long, List<Match>> playerMatchMap;

	@Value("${REPOSITORY_MATCH_FILE_NAME}")
	private String paramMatchFileName;

	/**
	 * Loads match list from file. Expected line format is: "winnerPlayerId looserPlayerId".
	 * Fields are space separated
	 */
	@PostConstruct
	public void loadMatchFile() {
		this.matchList = new ArrayList<Match>();
		this.playerMatchMap = new HashMap<Long, List<Match>>();
		List<String> strMatchList = super.readFileRepository(this.paramMatchFileName);
		if (strMatchList != null && strMatchList.size() > 0) {
			for (String line : strMatchList) {
				Long winnerPlayerId = Long.parseLong(line.split(" ")[0]);
				Long looserPlayerId = Long.parseLong(line.split(" ")[1]);
				
				Match match = new Match(winnerPlayerId, looserPlayerId, winnerPlayerId);
				this.matchList.add(match);
				
				if (!this.playerMatchMap.containsKey(winnerPlayerId)) {
					List<Match> playerMatchList = new ArrayList<Match>();
					this.playerMatchMap.put(winnerPlayerId, playerMatchList);
				}
				this.playerMatchMap.get(winnerPlayerId).add(match);
				
				if (!this.playerMatchMap.containsKey(looserPlayerId)) {
					List<Match> playerMatchList = new ArrayList<Match>();
					this.playerMatchMap.put(looserPlayerId, playerMatchList);
				}
				this.playerMatchMap.get(looserPlayerId).add(match);
			}
		} else {
			LOG.warn("MatchFileRepository-loadMatchFile - Match list is empty");
		}
	}

	@Override
	public List<Match> list() {
		return this.matchList.stream().collect(Collectors.toList());
	}

	@Override
	public List<Match> list(Long playerId) {
		return this.matchList.stream().filter(match -> match.isPlayersMatch(playerId)).collect(Collectors.toList());
	}
	
}
