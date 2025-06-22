package com.tech.domain.livre.port;

import com.tech.domain.livre.entity.Livre;
import com.tech.domain.livre.entity.LivreDTO;

import java.util.ArrayList;
import java.util.Optional;

public interface IManageLivre {

    Long count();

    Optional<Livre> findById(Long id);

    Livre save(Livre livre);

    Livre create(LivreDTO livreDTO);

    ArrayList<Livre> findAllByIsbnAndIdNot(String isbn, Long id);

    ArrayList<Livre> search(String auteur, String categorie, boolean disponible);

    ArrayList<Livre> searchOr(String auteur, String categorie, boolean disponible);

    boolean removeById(Long id);

    Long countAllByDisponible(boolean disponible);



}
