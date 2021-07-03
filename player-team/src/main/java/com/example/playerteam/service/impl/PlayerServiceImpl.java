package com.example.playerteam.service.impl;

import com.example.playerteam.entity.*;
import com.example.playerteam.repository.PlayerRepository;
import com.example.playerteam.service.PlayerService;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {
	private final PlayerRepository playerRepository;

	@Override
	public List<Player> findAll() {
		return playerRepository.findAll();
	}

	@Override
	public Player findById(Integer playerId) {
		return playerRepository.findById(playerId)
				.orElseThrow(() -> new NoSuchElementException("PlayerService.notFound"));
	}

	@Override
	public Player save(Player player) {
		return playerRepository.save(player);
	}

	@Override
	public Player update(Player player) {
		return playerRepository.save(player);
	}

	@Override
	public void deleteById(Integer playerId) {
		playerRepository.deleteById(playerId);
	}


}