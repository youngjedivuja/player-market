package com.example.transfers.service;

import com.example.transfers.entity.*;
import java.util.Collection;
import java.util.List;

public interface TransferService {

	List<Transfer> findAll();

	Transfer save(Transfer transfer);

	Transfer update(Transfer transfer);

	Transfer findById(Integer transferId);

	void deleteById(Integer transferId);

}