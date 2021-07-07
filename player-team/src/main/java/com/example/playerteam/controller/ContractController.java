package com.example.playerteam.controller;

import com.example.playerteam.entity.Contract;
import com.example.playerteam.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contracts")
@RequiredArgsConstructor
public class ContractController {
    private final ContractService contractService;

    @GetMapping
    public ResponseEntity<List<Contract>> getAllContact(){
        return ResponseEntity.ok(contractService.findAll());
    }

    @PostMapping
    public ResponseEntity<Contract> save(@RequestBody Contract contract){
        return ResponseEntity.ok(contractService.save(contract));
    }

    @PutMapping
    public ResponseEntity<Contract> update(@RequestBody Contract contract){
        return ResponseEntity.ok(contractService.update(contract));
    }

    @GetMapping("/final/{playerId}")
    public ResponseEntity<Contract> getFinalContractByPlayerId(@PathVariable Integer playerId){
        return ResponseEntity.ok(contractService.findFinalContractByPlayerId(playerId));
    }
}
