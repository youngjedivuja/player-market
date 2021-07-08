package com.example.transfers.service;

import com.example.transfers.entity.Transfer;
import com.example.transfers.entity.external.Contract;
import com.example.transfers.entity.external.Player;
import com.example.transfers.entity.external.Team;
import com.example.transfers.exception.IllegalTeamTransferException;
import com.example.transfers.repository.TransferRepository;
import com.example.transfers.service.impl.PlayerServiceImpl;
import com.example.transfers.service.impl.TransferServiceImpl;
import com.netflix.discovery.converters.Auto;
import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class TransferServiceTest {
    @Autowired
    TransferServiceImpl transferService;
    @Autowired
    PlayerService playerService;
    @Autowired
    ContractService contractService;
    @Autowired
    TeamService teamService;

    Player player;
    Team toTeam;
    Team fromTeam;
    Float commission;
    Contract contract;

    //set up valid data before each test
    @BeforeEach
    void setup() {
        toTeam = new Team();
        toTeam.setId(1);
        toTeam.setName("team1");

        fromTeam = new Team();
        fromTeam.setId(2);
        fromTeam.setName("team2");

        player = new Player();
        player.setId(8);
        player.setFirstName("firstName");
        player.setLastName("lastName");
        player.setAge(12);

        contract = new Contract();
        contract.setPlayer(player);
        contract.setTeam(fromTeam);
        contract.setStartDate(LocalDate.now());

        commission = 12f;
    }

    @Test
    void test_ageIsNull() {
        player.setAge(null);
        assertThrows(IllegalArgumentException.class, () -> transferService.calculateTransferFee(contract, fromTeam, toTeam, player, commission));
    }

    @Test
    void test_ageIsLessThanZero() {
        player.setAge(-4);
        assertThrows(IllegalArgumentException.class, () -> transferService.calculateTransferFee(contract, fromTeam, toTeam, player, commission));
    }

    @Test
    void test_fromTeamIsEqualsToToTeam() {
        toTeam = fromTeam;
        assertThrows(IllegalTeamTransferException.class, () -> transferService.calculateTransferFee(contract, fromTeam, toTeam, player, commission));
    }

    @Test
    void test_lastTeamIsEqualsToToTeam() {
        contract.setTeam(toTeam);
        assertThrows(IllegalTeamTransferException.class, () -> transferService.calculateTransferFee(contract, fromTeam, toTeam, player, commission));
    }

    @Test
    void test_saveTransfer() {
        Player savePlayer = new Player();
        savePlayer.setFirstName("test");
        savePlayer.setLastName("test");
        savePlayer.setAge(10);
        savePlayer = playerService.save(savePlayer);

        Team fromTeam = new Team();
        fromTeam.setName("fromTeam");
        fromTeam = teamService.save(fromTeam);

        Team toTeam = new Team();
        toTeam.setName("toTeam");
        toTeam = teamService.save(toTeam);

        Contract contract = new Contract();
        contract.setStartDate(LocalDate.now().minusMonths(3));
        contract.setPlayer(savePlayer);
        contract.setTeam(fromTeam);
        contract = contractService.save(contract);


        Transfer transfer = new Transfer();
        transfer.setCommission(commission);
        transfer.setFromTeamId(fromTeam.getId());
        transfer.setPlayerId(savePlayer.getId());
        transfer.setToTeamId(toTeam.getId());
        Transfer savedTransfer = transferService.save(transfer);

        assertEquals(280000, savedTransfer.getTransferFee());

    }

}
