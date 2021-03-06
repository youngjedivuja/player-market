package com.example.transfers.service;

import com.example.transfers.entity.external.Contract;

public interface ContractService {
    Contract save(Contract contract);

    Contract update(Contract contract);

    Contract findFinalContractByPlayerId(Integer playerId);
}
