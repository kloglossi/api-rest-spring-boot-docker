package com.tech.domain.livre.entity;


import com.tech.domain.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "livre")
public class Livre extends BaseEntity {

    private String nom;
    private String titre;
    private boolean disponible;
    private String categorie;
    private boolean isBn;

}
