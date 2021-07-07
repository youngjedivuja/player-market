package com.example.transfers.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import lombok.*;
import org.springframework.data.annotation.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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