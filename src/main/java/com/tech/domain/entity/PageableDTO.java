package com.tech.domain.entity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Setter()
@Getter
@Data()
public class PageableDTO {

    @Min(1)
    int pageNo;

    @Min(1)
    int itemsByPage;

    @NotEmpty
    String sortDir;

}
