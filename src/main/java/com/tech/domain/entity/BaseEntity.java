package com.tech.domain.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public abstract class BaseEntity extends AuditableEntity<LocalDateTime> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id =-1L;

}
