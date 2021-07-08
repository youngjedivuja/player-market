package com.example.transfers.entity.external;

import com.example.transfers.entity.Auditable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/*
 Value object for external Person entity
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class Player extends Auditable {
    private Integer id;
    private String firstName;
    private String lastName;
    private Integer age;
}
