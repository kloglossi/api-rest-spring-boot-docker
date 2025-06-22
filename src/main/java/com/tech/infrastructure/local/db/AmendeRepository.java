package com.tech.infrastructure.local.db;

import com.tech.domain.amende.enity.Amende;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AmendeRepository extends JpaRepository<Amende,Long> {

    List<Amende> findAllByPretId(Long pretId);

}
