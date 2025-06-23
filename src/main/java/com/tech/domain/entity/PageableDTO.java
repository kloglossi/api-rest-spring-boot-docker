package com.tech.domain.entity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Setter()
@Getter
@Data()
public class PageableDTO {

    @NotNull(message = "pageNo ne doit pas être  nulle")
    @Min(value = 1,message = "pageNo doit être supérieur à 0")
    Integer pageNo;

    @NotNull(message = "itemsByPage ne doit pas être  nulle")
    @Min(value = 1,message = "itemsByPage doit être supérieur à 0")
    Integer itemsByPage;

    @NotEmpty(message = "Le champ sortDir est requis")
    String sortDir;

}
