package com.tech.infrastructure.local.db;

import com.tech.domain.livre.entity.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface LivreRepository extends JpaRepository<Livre,Long> {

    ArrayList<Livre> findAllByIsbnAndIdNot(String isbn, Long id);

}
