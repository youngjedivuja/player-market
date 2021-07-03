package com.example.transfers.repository.external.impl;

import com.example.transfers.entity.external.Team;
import com.example.transfers.repository.external.AbstractRepository;
import com.example.transfers.repository.external.TeamRepository;
import org.springframework.beans.factory.annotation.Value;

import java.net.URI;
import java.util.Optional;

public class TeamRepositoryImpl extends AbstractRepository<Team> implements TeamRepository {

    //url for teams service
    @Value("${teams.service.url}")
    private String teamServiceUrl;

    @Override
    public Optional<Team> findById(Integer id) {
        URI uri = getURI(teamServiceUrl, String.valueOf(id));
        return getForEntity(uri, Team.class);
    }
}
