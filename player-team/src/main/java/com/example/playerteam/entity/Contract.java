package com.example.playerteam.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@Table(name = "contract")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class Contract extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "contract_id")
    private Integer id;
    @JoinColumn(name = "team_fk", referencedColumnName = "team_id")
    @ManyToOne
    private Team team;
    @JoinColumn(name = "player_fk", referencedColumnName = "player_id")
    @ManyToOne
    private Player player;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;
}
