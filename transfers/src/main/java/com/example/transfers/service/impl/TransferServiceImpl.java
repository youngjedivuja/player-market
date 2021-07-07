package com.example.transfers.service.impl;

import com.example.transfers.entity.*;
import com.example.transfers.entity.external.Contract;
import com.example.transfers.entity.external.Player;
import com.example.transfers.entity.external.Team;
import com.example.transfers.exception.IllegalTeamTransferException;
import com.example.transfers.repository.TransferRepository;
import com.example.transfers.service.ContractService;
import com.example.transfers.service.PlayerService;
import com.example.transfers.service.TeamService;
import com.example.transfers.service.TransferService;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {
    private final TransferRepository transferRepository;
    private final PlayerService playerService;
    private final TeamService teamService;
    private final ContractService contractService;
    private static final int TRANSFER_COEFFICIENT = 100_000;

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
        Team toTeam = teamService.findById(transfer.getToTeamId());
        Team fromTeam = teamService.findById(transfer.getFromTeamId());

        Contract lastContract = contractService.findFinalContractByPlayerId(player.getId());

        transfer.setTransferFee(calculateTransferFee(lastContract, fromTeam, toTeam, player, transfer.getCommission()));

        //Ending last contract
        lastContract.setEndDate(LocalDate.now());
        contractService.update(lastContract);

        //New contract to be saved
        Contract contract = new Contract(LocalDate.now(), toTeam, player);
        contractService.save(contract);



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

    public Float calculateTransferFee(Contract lastContract, Team fromTeam, Team toTeam, Player player, Float commission) {
        Integer monthsOfExperience = playerService.calculateExperience(player.getId());
        Integer age = player.getAge();

        //last or current player's team
        Team lastTeam = lastContract.getTeam();

        //since months of experience is being calculated and date miscalculations are handled in that method already, there is no need to check
        //or throw any exceptions regarding experience, in the worst case the value will be zero which is entirely possible
        if (age == null || age < 0)
            throw new IllegalArgumentException("Player cannot be transferred if 'age' is null or less than zero");
        if (lastTeam.equals(toTeam) || toTeam.equals(fromTeam))
            throw new IllegalTeamTransferException();

        float feeWithoutCommission = monthsOfExperience * TRANSFER_COEFFICIENT / (float) age;
        return feeWithoutCommission + feeWithoutCommission / (commission / 100);
    }


}