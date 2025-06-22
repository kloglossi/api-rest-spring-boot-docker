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

    @NotBlank(message = "La date de pret est requise")
    private String datePret;

    //@NotBlank(message = "Le champ date de retour est requise au format yyyy-MM-dd")
    private String dateRetour;

    private String statut;

    @NotBlank(message = "Le livre  est requis")
    private String livreId;

    @NotBlank(message = "Le membre  est requis")
    private String membreId;

}
