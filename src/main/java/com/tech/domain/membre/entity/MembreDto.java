package com.tech.domain.membre.entity;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class MembreDto {

    String id= "-1L";

    @NotBlank(message = "Le champ nom est requis")
    @NotNull(message = "Le champ nom est null")
    String nom ;

    @Email(message = "Email invalide")
    @Column(unique = true)
    String email ;

    //@NotBlank(message = "Le champ date d'adhésion est requis")
    @NotNull(message = "Le champ date d'adhésion est null")
    LocalDate dateMembership;

    String statut;

}
