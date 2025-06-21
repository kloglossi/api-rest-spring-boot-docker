package com.tech.domain.membre.entity;

import com.tech.application.config.validator.UniqueEmail;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Future;
import java.time.LocalDate;

@Data
@Builder
public class MembreDto {

    String id= "-1L";

    @NotBlank(message = "Le champ nom est requis")
    @NotEmpty
    String nom ;

    @NotEmpty
    @Email(message = "Email invalide")
    String email ;

    //@NotBlank(message = "Le champ date d'adhésion est requis")
    @NotEmpty
    @NotBlank(message = "Le champ date d'adhésion est requise au format yyyy-MM-dd")
    String dateMembership;

    String statut;

}
