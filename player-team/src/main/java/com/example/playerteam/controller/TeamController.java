package com.example.playerteam.controller;

import com.example.playerteam.entity.*;
import com.example.playerteam.service.*;
import java.util.List;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teams")
@RequiredArgsConstructor
public class TeamController {
	private final TeamService teamService;

	@GetMapping
	public ResponseEntity<List<Team>> getAllTeams() {
		return ResponseEntity.ok(teamService.findAll());
	}

	@GetMapping("/{teamId}")
	public ResponseEntity<Team> getTeamById(@PathVariable Integer teamId) {
		return ResponseEntity.ok(teamService.findById(teamId));
	}

	@PostMapping
	public ResponseEntity<Team> saveTeam(@RequestBody Team team) {
		return ResponseEntity.status(HttpStatus.CREATED).body(teamService.save(team));
	}

	@PutMapping
	public ResponseEntity<Team> updateTeam(@RequestBody Team team) {
		return ResponseEntity.ok(teamService.update(team));
	}

	@DeleteMapping("/{teamId}")
	public void deleteTeamById(@PathVariable Integer teamId) {
		teamService.deleteById(teamId);
	}

}

