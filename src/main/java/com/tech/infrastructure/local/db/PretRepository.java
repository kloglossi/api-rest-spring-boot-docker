package com.tech.infrastructure.local.db;

import com.tech.domain.pret.entity.Pret;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PretRepository extends JpaRepository<Pret,Long> {

    Long countAllByMembreIdAndStatut(Long membreId, String statut);

    List<Pret> findAllByIdAndStatutIsNotAndDateRetourIsNotNull(
            Long id, String statut
    );


    List<Pret> findAllByStatutAndDateRetourIsNull(String statut);

    Long countAllByStatutAndDateRetourIsNull(String statut);

    List<Pret> findAllByStatutAndDateRetourIsNotNull(String statut);

    Long countAllByStatutAndDateRetourIsNotNull(String statut);

    List<Pret> findAllById(Long id);

    List<Pret> findAllByMembreId(Long membreId);

    List<Pret> findAllByMembreIdAndLivreId(Long membreId, Long livreId);

    List<Pret> findAllByMembreIdAndLivreIdAndStatut(
            Long membreId, Long livreId, String statut
    );

}
