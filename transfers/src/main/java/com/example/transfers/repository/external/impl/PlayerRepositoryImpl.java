package com.example.transfers.repository.external.impl;

import com.example.transfers.entity.external.Player;
import com.example.transfers.repository.external.AbstractRepository;
import com.example.transfers.repository.external.PlayerRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.net.URI;
import java.util.Optional;

@Repository
public class PlayerRepositoryImpl extends AbstractRepository<Player> implements PlayerRepository {

    //url for players service
    @Value("${players.service.url}")
    private String playerServiceUrl;

    public PlayerRepositoryImpl() {
        super(Player.class);
    }

    @Override
    public Optional<Player> findById(Integer id) {
        URI uri = getURI(playerServiceUrl, String.valueOf(id));
        return getOptionalForEntity(uri, Player.class);
    }

    @Override
    public Integer calculateExperience(Integer id) {
        URI uri = getURI(playerServiceUrl, "experience/" + id);
        return getResponseForEntity(uri).getBody();
    }
}
