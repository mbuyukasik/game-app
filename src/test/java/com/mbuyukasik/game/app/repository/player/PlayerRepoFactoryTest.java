package com.mbuyukasik.game.app.repository.player;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mbuyukasik.game.app.GameAppTest;

/**
 * PlayerRepoFactoryTest is test class of PlayerRepoFactory
 * @author TTMBUYUKASIK
 * @version 1
 */
public class PlayerRepoFactoryTest extends GameAppTest {

	@Autowired
	private PlayerRepoFactory playerRepoFactory;
	
	@Test
	public void getPlayerRepositoryTest() {
		IPlayerRepository playerRepository = playerRepoFactory.getPlayerRepository();
		assertTrue("Type of player repository object is incorrect.", 
				playerRepository != null && playerRepository instanceof PlayerFileRepository);
		
	}
	
}
