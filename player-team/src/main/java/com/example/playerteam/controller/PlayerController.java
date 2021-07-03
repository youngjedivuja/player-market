package com.example.playerteam.controller;

import com.example.playerteam.entity.*;
import com.example.playerteam.service.*;
import java.util.List;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/players")
@RequiredArgsConstructor
public class PlayerController {
	private final PlayerService playerService;

	@GetMapping
	public ResponseEntity<List<Player>> getAllPlayers() {
		return ResponseEntity.ok(playerService.findAll());
	}

	@GetMapping("/{playerId}")
	public ResponseEntity<Player> getPlayerById(@PathVariable Integer playerId) {
		return ResponseEntity.ok(playerService.findById(playerId));
	}

	@PostMapping
	public ResponseEntity<Player> savePlayer(@RequestBody Player player) {
		return ResponseEntity.status(HttpStatus.CREATED).body(playerService.save(player));
	}

	@PutMapping
	public ResponseEntity<Player> updatePlayer(@RequestBody Player player) {
		return ResponseEntity.ok(playerService.update(player));
	}

	@DeleteMapping("/{playerId}")
	public void deletePlayerById(@PathVariable Integer playerId) {
		playerService.deleteById(playerId);
	}

	@GetMapping("/experience/{playerId}")
	public ResponseEntity<Integer> getPlayerExperience(@PathVariable Integer playerId){
		return ResponseEntity.ok(playerService.calculateExperience(playerId));
	}

}

