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
     @NotEmpty
     String auteur;

     @NotBlank(message = "Le champ titre est requis")
     @NotEmpty
     String titre;

     boolean disponible=true;

     @NotBlank(message = "Le champ categorie est requis")
     @NotEmpty
     String categorie;

     @NotBlank(message = "Le champ isbn est requis")
     @NotEmpty
     @Min(10)
     @Max(13)
     String isbn;

}
