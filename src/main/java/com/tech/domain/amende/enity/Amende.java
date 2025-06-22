package com.tech.domain.amende.enity;

import com.tech.domain.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "amende")
public class Amende extends BaseEntity {

    private Long pretId;

    private int nombreJoursRetard;

    private double coutParJour;

    private double coutTotal;

}
