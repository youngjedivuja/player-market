package com.example.transfers.repository.external;

import com.example.transfers.entity.external.Contract;

import java.util.Optional;

public interface ContractRepository {
    Contract save(Contract contract);
}
