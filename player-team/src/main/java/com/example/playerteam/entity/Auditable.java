package com.example.playerteam.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Auditable implements Serializable {

	@CreatedDate
	@JsonIgnore
	private LocalDateTime createdDate;
	@LastModifiedDate
	@JsonIgnore
	private LocalDateTime lastModifiedDate;
	@LastModifiedBy
	@JsonIgnore
	private String lastModifiedBy;
	@JsonIgnore
	private Integer recordStatus = 1;

}