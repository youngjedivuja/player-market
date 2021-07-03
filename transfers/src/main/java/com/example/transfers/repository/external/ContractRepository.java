package com.example.transfers.repository.external;

import com.example.transfers.entity.external.Contract;

import java.util.Optional;

public interface ContractRepository {
    Contract save(Contract contract);

    Optional<Contract> findFinalContractByPlayerId(Integer playerId);

    Contract update(Contract contract);
}
