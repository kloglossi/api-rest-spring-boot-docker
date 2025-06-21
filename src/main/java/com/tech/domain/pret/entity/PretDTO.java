package com.tech.domain.pret.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class PretDTO {

    String id= "-1L";

    @NotEmpty
    @NotBlank(message = "Le champ date de pret est requise au format yyyy-MM-dd")
    private String datePret;

    @NotEmpty
    @NotBlank(message = "Le champ date de retour est requise au format yyyy-MM-dd")
    private String dateRetour;

    private String statut;

    @NotBlank(message = "Le livre  est requis")
    private String livreId;

    @NotBlank(message = "Le livre  est requis")
    private String membreId;

}
