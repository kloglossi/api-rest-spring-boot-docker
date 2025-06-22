package com.tech.domain.amende.port;

import com.tech.domain.amende.enity.Amende;

import java.util.List;
import java.util.Optional;

public interface IManageAmende {

    Long count();

    Amende save(Amende amende);

    List<Amende> findAllByPretId(Long pretId);

    List<Amende> findAll();

    Optional<Amende> findById(Long id);

}
