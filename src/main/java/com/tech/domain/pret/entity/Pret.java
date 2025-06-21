package com.tech.domain.pret.entity;

import com.tech.domain.entity.BaseEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class Pret extends BaseEntity {

   private LocalDate datePret;

   private LocalDate dateRetour;

   private String statut;

   private Long livreId;

   private Long membreId;

}
