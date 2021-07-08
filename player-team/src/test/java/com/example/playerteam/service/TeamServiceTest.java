package com.example.playerteam.service;

import com.example.playerteam.entity.Team;
import com.example.playerteam.repository.PlayerRepository;
import com.example.playerteam.repository.TeamRepository;
import com.example.playerteam.service.impl.TeamServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith({MockitoExtension.class})
public class TeamServiceTest {

    @Mock
    TeamRepository teamRepository;

    @InjectMocks
    TeamServiceImpl teamService;

    @Test
    void test_findById() {
        //given
        Team buyer = new Team();
        given(teamRepository.findById(1)).willReturn(Optional.of(buyer));

        //when
        Team foundTeam = teamService.findById(1);

        //then
        assertThat(foundTeam).isNotNull();
        then(teamRepository).should().findById(anyInt());
        then(teamRepository).shouldHaveNoMoreInteractions();
    }

    @Test
    void test_deleteById() {
        //given - none

        //when
        teamService.deleteById(1);

        //then
        then(teamRepository).should().deleteById(1);
    }

    @Test
    void test_saveTeam() {
        //given
        Team team = new Team();
        given(teamRepository.save(any(Team.class))).willReturn(team);

        //when
        Team savedTeam = teamService.save(new Team());

        //then
        then(teamRepository).should().save(any(Team.class));
        assertThat(savedTeam).isNotNull();
    }


    @Test
    void test_findAllTeam() {
        //given
        Team team = new Team();
        List<Team> teams = new ArrayList<>();
        teams.add(team);
        given(teamRepository.findAll()).willReturn(teams);

        //when
        List<Team> foundTeams = teamService.findAll();

        //then
        then(teamRepository).should().findAll();
        assertThat(foundTeams).hasSize(1);
    }
}
