package com.example.playerteam.service;

import com.example.playerteam.entity.Contract;

import java.util.List;

public interface ContractService {
    List<Contract> findAll();

    Contract save(Contract contract);

    Contract update(Contract contract);

    Contract findById(Integer contractId);

    void deleteById(Integer contractId);
}
