package com.tech.domain.membre.entity;


import com.tech.application.config.validator.UniqueEmail;
import com.tech.domain.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter()
@Getter()
@Entity
@Table(name = "membre")
public class Membre extends BaseEntity {

   private String nom ;

   private String email ;

   private LocalDate dateMembership = null;

   private String statut;

}
