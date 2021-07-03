package com.example.transfers.repository.external;

import com.example.transfers.entity.external.Player;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@Repository
public class PlayerRepositoryImpl extends AbstractRepository<Player> implements PlayerRepository{

    //url for players service
    @Value("${players.service.url}")
    private String playerServiceUrl;

    @Override
    public Optional<Player> findById(Integer id) {
        URI uri = getURI(playerServiceUrl, String.valueOf(id));
        return getForEntity(uri, Player.class);
    }
}
