package com.example.playerteam.service.impl;

import com.example.playerteam.entity.Contract;
import com.example.playerteam.repository.ContractRepository;
import com.example.playerteam.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {
    private final ContractRepository contractRepository;

    @Override
    public List<Contract> findAll() {
        return contractRepository.findAll();
    }

    @Override
    public Contract findById(Integer playerId) {
        return contractRepository.findById(playerId)
                .orElseThrow(() -> new NoSuchElementException("ContractService.notFound"));
    }

    @Override
    public Contract save(Contract contract) {
        return contractRepository.save(contract);
    }

    @Override
    public Contract update(Contract contract) {
        return contractRepository.save(contract);
    }

    @Override
    public void deleteById(Integer contractId) {
        contractRepository.deleteById(contractId);
    }
}
