package com.example.playerteam.service;

import com.example.playerteam.entity.*;
import java.util.Collection;
import java.util.List;

public interface PlayerService {

	List<Player> findAll();

	Player save(Player player);

	Player update(Player player);

	Player findById(Integer playerId);

	void deleteById(Integer playerId);

}