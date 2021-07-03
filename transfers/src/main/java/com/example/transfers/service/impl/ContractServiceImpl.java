package com.example.transfers.service.impl;

import com.example.transfers.entity.external.Contract;
import com.example.transfers.repository.external.ContractRepository;
import com.example.transfers.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;

    @Override
    public Contract save(Contract contract) {
        return contractRepository.save(contract);
    }

    @Override
    public Contract update(Contract contract) {
        return contractRepository.update(contract);
    }

    @Override
    public Contract findFinalContractByPlayerId(Integer playerId) {
        return contractRepository.findFinalContractByPlayerId(playerId).orElseThrow(() -> new NoSuchElementException("ContractServiceImpl.notFound"));
    }
}
