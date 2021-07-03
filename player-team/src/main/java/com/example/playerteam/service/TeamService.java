package com.example.playerteam.service;

import com.example.playerteam.entity.*;
import java.util.Collection;
import java.util.List;

public interface TeamService {

	List<Team> findAll();

	Team save(Team team);

	Team update(Team team);

	Team findById(Integer teamId);

	void deleteById(Integer teamId);

	Team findLastTeamByPlayerId(Integer playerId);

}