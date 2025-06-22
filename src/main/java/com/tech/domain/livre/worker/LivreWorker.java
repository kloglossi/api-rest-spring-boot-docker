package com.tech.domain.livre.worker;

import com.tech.domain.livre.entity.Livre;
import com.tech.domain.livre.entity.LivreDTO;
import com.tech.domain.livre.port.LivreDomain;
import com.tech.infrastructure.local.db.LivreRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class LivreWorker implements LivreDomain {

    @Autowired
    private LivreRepository livreRepository;

    @Override
    public Long count() {
        return livreRepository.count();
    }

    @Override
    public Optional<Livre> findById(Long id) {
        return livreRepository.findById(id);
    }

    @Override
    public Livre save(@Valid Livre livre) {
        return livreRepository.save(livre);
    }

    @Override
    public Livre create(LivreDTO livreDTO) {

        Livre livre = Livre.builder()
                .auteur(livreDTO.getAuteur())
                .titre(livreDTO.getTitre())
                .disponible(Boolean.parseBoolean(livreDTO.getDisponible()))
                .categorie(livreDTO.getCategorie())
                .isbn(livreDTO.getIsbn())
                .build();
        return  livreRepository.save(livre);
    }

    @Override
    public ArrayList<Livre> findAllByIsbnAndIdNot(String isbn, Long id) {
        return livreRepository.findAllByIsbnAndIdNot(isbn, id);
    }

    @Override
    public ArrayList<Livre> search(String auteur, String categorie, boolean disponible) {
        return livreRepository.findAllByAuteurContainingAndCategorieContainingAndDisponible(
                auteur, categorie, disponible);
    }

    @Override
    public ArrayList<Livre> searchOr(String auteur, String categorie, boolean disponible) {
        return livreRepository.findAllByAuteurContainingOrCategorieContainingOrDisponible(
                auteur, categorie, disponible);
    }

    @Override
    public boolean removeById(Long id) {
        boolean success = false;
        try {
            livreRepository.deleteById(id);
            success = true;
        }catch (Exception e){
            success =false;
        }
        return success;
    }

    @Override
    public Long countAllByDisponible(boolean disponible) {
        return livreRepository.countAllByDisponible(disponible);
    }

}
