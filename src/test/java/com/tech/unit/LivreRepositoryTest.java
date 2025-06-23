package com.tech.unit;

import com.tech.BiblioDevApplication;
import com.tech.domain.livre.entity.Livre;
import com.tech.infrastructure.local.db.LivreRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = BiblioDevApplication.class)
public class LivreRepositoryTest {

    @Autowired
    private LivreRepository livreRepository;

    @AfterEach
    void tearDown(){
        livreRepository.deleteAll();
    }

    @Test
    void verifierSiLeLivreExistParAuteur(){
        String auteur="Ben carlos";

        int min = 10000;
        int max = 100000;
        int randomNumber = (int)(Math.random() * (max - min + 1)) + min;
        String isbn ="99400049"+randomNumber;

        //données
        Livre livre = Livre.builder()
                .titre("Le vent")
                .auteur(auteur)
                .categorie("nature")
                .disponible(true)
                .isbn(isbn)
                .build();
        livreRepository.save(livre);

        //Quand
        boolean res = livreRepository.existsByAuteur(auteur);

        //Alors
        assertThat(res).isTrue();

    }

    @Test
    void verifierSiLaCategorieDuLivreEst(){

        int min = 10000;
        int max = 400000;
        int randomNumber = (int)(Math.random() * (max - min + 1)) + min;
        String isbn ="95450999"+randomNumber;

        String categorie="science";
        //donnée
        Livre livre = Livre.builder()
                .titre("Le quotidien")
                .auteur("Luis mendez")
                .categorie(categorie)
                .disponible(true)
                .isbn(isbn)
                .build();
        Livre lv = livreRepository.save(livre);

         //Quand
        String res = lv.getCategorie();

        //Alors
        assertThat(res).isEqualTo(categorie);

    }

    @Test
    void verifierSiLeLivreExistParIsbn(){

        int min = 10000;
        int max = 800000;
        int randomNumber = (int)(Math.random() * (max - min + 1)) + min;
        String isbn ="90410999"+randomNumber;

        //données
        Livre livre = Livre.builder()
                .titre("La paix en temps de guerre")
                .auteur("Ricardo Luis")
                .categorie("histoire")
                .disponible(true)
                .isbn(isbn)
                .build();
        livreRepository.save(livre);

        //Quand
        boolean res = livreRepository.existsByIsbn(isbn);

        //Alors
        assertThat(res).isTrue();

    }

    @Test
    void rechercheUnLivreInexitantParId(){

        //donnée
        Long id =-1L;

        //Quand
        Optional<Livre> lv = livreRepository.findById(id);

        //Alors
        assertThat(lv).isEmpty();

    }

}
