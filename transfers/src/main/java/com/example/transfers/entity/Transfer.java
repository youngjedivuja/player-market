package com.example.transfers.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.*;
import java.util.*;
import javax.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "transfer")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class Transfer extends Auditable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name = "transfer_id")
	private Integer id;
	@Column(name = "from_team_id")
	private Integer fromTeamId;
	@Column(name = "to_team_id")
	private Integer toTeamId;
	@Column(name = "player_id")
	private Integer playerId;
	@Column(name = "transfer_fee")
	private Float transferFee;
	@Column(name = "commission")
	private Float commission;
	
}