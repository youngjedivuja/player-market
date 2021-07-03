package com.example.transfers.service.impl;

import com.example.transfers.entity.*;
import com.example.transfers.entity.external.Player;
import com.example.transfers.repository.TransferRepository;
import com.example.transfers.service.PlayerService;
import com.example.transfers.service.TransferService;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {
	private final TransferRepository transferRepository;
	private final PlayerService playerService;

	@Override
	public List<Transfer> findAll() {
		return transferRepository.findAll();
	}

	@Override
	public Transfer findById(Integer transferId) {
		return transferRepository.findById(transferId)
				.orElseThrow(() -> new NoSuchElementException("TransferService.notFound"));
	}

	@Override
	public Transfer save(Transfer transfer) {
		Player player = playerService.findById(transfer.getPlayerId());
		return transferRepository.save(transfer);
	}

	@Override
	public Transfer update(Transfer transfer) {
		return transferRepository.save(transfer);
	}

	@Override
	public void deleteById(Integer transferId) {
		transferRepository.deleteById(transferId);
	}


}