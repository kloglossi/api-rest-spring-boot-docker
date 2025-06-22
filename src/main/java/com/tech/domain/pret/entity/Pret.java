package com.tech.domain.pret.entity;

import com.tech.domain.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "pret")
public class Pret extends BaseEntity {

   private LocalDate datePret;

   private LocalDate dateRetour;

   private String statut;

   private Long livreId;

   private Long membreId;

}
