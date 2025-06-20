package com.tech.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AuditableEntity <U>{

    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @CreatedDate
    U createdate = null;

    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @LastModifiedDate
    U editdate  = null;


}