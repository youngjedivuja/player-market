package com.example.transfers.entity.external;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class Contract {
    private Integer id;
    private LocalDate startDate;
    private LocalDate endDate;
    private Team team;
    private Player player;

    public Contract(LocalDate startDate, Team team, Player player) {
        this.startDate = startDate;
        this.team = team;
        this.player = player;
    }
}
