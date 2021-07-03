package com.example.playerteam.service.impl;

import com.example.playerteam.entity.*;
import com.example.playerteam.repository.TeamRepository;
import com.example.playerteam.service.TeamService;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {
	private final TeamRepository teamRepository;

	@Override
	public List<Team> findAll() {
		return teamRepository.findAll();
	}

	@Override
	public Team findById(Integer teamId) {
		return teamRepository.findById(teamId)
				.orElseThrow(() -> new NoSuchElementException("TeamService.notFound"));
	}

	@Override
	public Team save(Team team) {
		return teamRepository.save(team);
	}

	@Override
	public Team update(Team team) {
		return teamRepository.save(team);
	}

	@Override
	public void deleteById(Integer teamId) {
		teamRepository.deleteById(teamId);
	}


}