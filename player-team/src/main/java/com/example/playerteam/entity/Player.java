package com.example.playerteam.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.*;
import java.util.*;
import javax.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "player")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class Player extends Auditable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name = "player_id")
	private Integer id;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "age")
	private Integer age;
	@OneToMany(mappedBy = "player")
	@JsonIgnore
	@ToString.Exclude
	private List<Contract> contracts;
	
}