package com.tech.domain.membre.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class MembreDto {

    @NotBlank(message = "Le champ nom est requis")
    @NotNull(message = "Le champ nom est null")
    String nom ;

    @Email(message = "Email invalide")
    String email ;

    //@NotNull(message = "Le champ date d'adhésion est null")
    LocalDate dateMembership;

    String statut;

    public LocalDate getDateMembership() {
        return dateMembership;
    }

    public String getEmail() {
        return email;
    }

    public String getNom() {
        return nom;
    }

    public String getStatut() {
        return statut;
    }
}
