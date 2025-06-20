package com.tech.domain.entity;

import lombok.*;

@Setter()
@Getter
@Data()
public class PageableDTO {

    //@NotEmpty
    int pageNo;
    int itemsByPage;

    //@NotEmpty
    String sortDir;

    //@NotEmpty
    String sortField="id";

    int pageSize;


}
