package com.example.transfers.service.impl;

import com.example.transfers.entity.external.Team;
import com.example.transfers.repository.external.TeamRepository;
import com.example.transfers.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;

    @Override
    public Team findById(Integer id) {
        return teamRepository.findById(id).orElseThrow(() -> new NoSuchElementException("TeamService.notFound"));
    }

    @Override
    public Team findLastTeamByPlayerId(Integer playerId) {
        return teamRepository.findLastTeamByPlayerId(playerId).orElseThrow(() -> new NoSuchElementException("TeamService.team.notFound"));
    }

    @Override
    public Team save(Team team) {
        return teamRepository.save(team);
    }
}
