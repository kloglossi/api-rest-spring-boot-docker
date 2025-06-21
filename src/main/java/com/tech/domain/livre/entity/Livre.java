package com.tech.domain.livre.entity;


import com.tech.domain.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "livre")
public class Livre extends BaseEntity {

    private String  titre;

    private String  auteur;

    private boolean disponible;

    private String  categorie;

    private String  isbn;

}
