package com.example.transfers.repository.external.impl;

import com.example.transfers.entity.external.Contract;
import com.example.transfers.entity.external.Player;
import com.example.transfers.repository.external.AbstractRepository;
import com.example.transfers.repository.external.ContractRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.net.URI;
import java.util.Optional;

@Repository
public class ContractRepositoryImpl extends AbstractRepository<Contract> implements ContractRepository {

    //url for contract service
    @Value("${contract.service.url}")
    private String contractServiceUrl;

    public ContractRepositoryImpl() {
        super(Contract.class);
    }
    @Override
    public Contract save(Contract contract) {
        URI uri = getURI(contractServiceUrl);
        return postForEntity(uri, contract);
    }

    @Override
    public Optional<Contract> findFinalContractByPlayerId(Integer playerId) {
        URI uri = getURI(contractServiceUrl, "final/" + playerId);
        return getOptionalForEntity(uri, Contract.class);
    }

    @Override
    public Contract update(Contract contract) {
        URI uri = getURI(contractServiceUrl);
        System.out.println(contract);
        return putForEntity(uri, contract);
    }
}
