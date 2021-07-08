package com.example.transfers.service;

import com.example.transfers.entity.external.Player;

public interface PlayerService {

    Player findById(Integer id);

    Integer calculateExperience(Integer playerId);

    Player save(Player player);
}
