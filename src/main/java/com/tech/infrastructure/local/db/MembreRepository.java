package com.tech.infrastructure.local.db;


import com.tech.domain.membre.entity.Membre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MembreRepository extends JpaRepository<Membre,Long> {

    boolean existsByEmail(String email);

    Optional<Membre> findByEmailAndIdNot(String email, Long id);

    Long countAllByStatut(String statut);
}
