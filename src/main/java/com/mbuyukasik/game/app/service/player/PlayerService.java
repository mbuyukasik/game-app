package com.mbuyukasik.game.app.service.player;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbuyukasik.game.app.model.Player;
import com.mbuyukasik.game.app.repository.player.IPlayerRepository;
import com.mbuyukasik.game.app.repository.player.PlayerRepoFactory;
import com.mbuyukasik.game.app.service.IService;

/**
 * PlayerService is used as a proxy in front of repository class
 * Also used to keep business logic about Player entity
 * 
 * @author: mehmet buyukasik
 * @version 1.0
 */
@Service
public class PlayerService implements IService<Player> {
	
	private IPlayerRepository playerRepository;
	
	@Autowired
	public PlayerService(PlayerRepoFactory playerRepoFactory) {
		this.playerRepository = playerRepoFactory.getPlayerRepository();
	}
	
	public List<Player> list() {
		return this.playerRepository.list();
	}
	
	public Player get(Long id) {
		return this.playerRepository.get(id);
	}
	
}
