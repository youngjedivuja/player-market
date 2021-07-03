package com.example.playerteam.controller;

import com.example.playerteam.entity.Contract;
import com.example.playerteam.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contracts")
@RequiredArgsConstructor
public class ContractController {
    private final ContractService contractService;

    @PostMapping
    public ResponseEntity<Contract> save(@RequestBody Contract contract){
        return ResponseEntity.ok(contractService.save(contract));
    }
}
