package com.example.transfers.controller;

import com.example.transfers.entity.*;
import com.example.transfers.entity.external.Player;
import com.example.transfers.service.*;

import java.util.List;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transfers")
@RequiredArgsConstructor
public class TransferController {
    private final TransferService transferService;

    @GetMapping
    public ResponseEntity<List<Transfer>> getAllTransfers() {
        return ResponseEntity.ok(transferService.findAll());
    }

    @GetMapping("/{transferId}")
    public ResponseEntity<Transfer> getTransferById(@PathVariable Integer transferId) {
        return ResponseEntity.ok(transferService.findById(transferId));
    }

    @PostMapping
    public ResponseEntity<Transfer> saveTransfer(@RequestBody Transfer transfer) {
        return ResponseEntity.status(HttpStatus.CREATED).body(transferService.save(transfer));
    }

    @PutMapping
    public ResponseEntity<Transfer> updateTransfer(@RequestBody Transfer transfer) {
        return ResponseEntity.ok(transferService.update(transfer));
    }

    @DeleteMapping("/{transferId}")
    public void deleteTransferById(@PathVariable Integer transferId) {
        transferService.deleteById(transferId);
    }

}

