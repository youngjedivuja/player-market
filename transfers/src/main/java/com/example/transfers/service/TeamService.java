package com.example.transfers.service;

import com.example.transfers.entity.external.Team;

public interface TeamService {

    Team findById(Integer id);

    Team findLastTeamByPlayerId(Integer playerId);

    Team save(Team team);
}
