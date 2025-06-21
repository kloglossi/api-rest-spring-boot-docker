package com.tech.infrastructure.local.db;

import com.tech.domain.pret.entity.Pret;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PretRepository extends JpaRepository<Pret,Long> {

    Long countAllByMembreIdAndStatut(Long membreId, String statut);

}
