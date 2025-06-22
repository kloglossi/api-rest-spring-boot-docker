package com.tech.domain.amende.enity;

import com.tech.domain.entity.BaseEntity;
import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Amende extends BaseEntity {

    private Long pretId;

    private int nombreJoursRetard;

    private double coutParJour;

    private double coutTotal;

}
