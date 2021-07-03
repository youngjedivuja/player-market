package com.example.playerteam.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.*;
import java.util.*;
import javax.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "team")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class Team extends Auditable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name = "team_id")
	private Integer id;
	@Column(name = "name")
	private String name;
	@OneToMany(mappedBy = "team")
	@JsonIgnore
	@ToString.Exclude
	private List<Contract> contracts;
	
}