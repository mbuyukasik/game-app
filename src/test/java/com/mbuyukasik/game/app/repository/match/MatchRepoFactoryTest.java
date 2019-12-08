package com.mbuyukasik.game.app.repository.match;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mbuyukasik.game.app.GameAppTest;

/**
 * MatchRepoFactoryTest is test class of MatchRepoFactory
 * @author TTMBUYUKASIK
 * @version 1
 */
public class MatchRepoFactoryTest extends GameAppTest {

	@Autowired
	private MatchRepoFactory matchRepoFactory;
	
	@Test
	public void getMatchRepositoryTest() {
		IMatchRepository matchRepository = matchRepoFactory.getMatchRepository();
		assertTrue("Type of match repository object is incorrect.", 
				matchRepository != null && matchRepository instanceof MatchFileRepository);
		
	}
	
}
