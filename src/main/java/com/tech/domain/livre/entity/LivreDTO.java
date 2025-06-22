package com.tech.domain.livre.entity;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LivreDTO {

     String id="-1";

     @NotBlank(message = "Le champ auteur est requis")
     //@NotEmpty(message = "Le champ auteur est requis")
     String auteur;

     @NotBlank(message = "Le champ titre est requis")
     String titre;

     String disponible="true";

     @NotBlank(message = "Le champ categorie est requis")
     //@NotEmpty
     String categorie;

     @NotBlank(message = "Le champ isbn est requis, il doit être supérieur à 9 caractères")
     //@NotEmpty
     String isbn;

}
