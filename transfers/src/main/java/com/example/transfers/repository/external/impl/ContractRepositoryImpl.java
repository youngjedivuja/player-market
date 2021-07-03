package com.example.transfers.repository.external.impl;

import com.example.transfers.entity.external.Contract;
import com.example.transfers.repository.external.AbstractRepository;
import com.example.transfers.repository.external.ContractRepository;
import org.springframework.beans.factory.annotation.Value;

import java.net.URI;

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
}
