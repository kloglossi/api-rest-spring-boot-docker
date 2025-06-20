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


  Membre save(MembreDto membreDto);

  Optional<Membre> findById(Long id);

}
