package com.tech.domain.membre.port;



import com.tech.domain.entity.PageableDTO;
import com.tech.domain.membre.entity.Membre;
import com.tech.domain.membre.entity.MembreDto;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface IManageMembre {

   Page<Membre> findPaginated(
            PageableDTO pageable
   );

   Long count();

  boolean findByEmail(String email);

  Membre save(MembreDto membreDto);

  Membre create(Membre membre);

  Optional<Membre> findById(Long id);

  Optional<Membre> findByEmailAndIdNot(String email, Long id);

    Long countAllByStatut(String statut);

}
