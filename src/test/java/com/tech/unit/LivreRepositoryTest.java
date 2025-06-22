package com.tech.unit;

import com.tech.domain.livre.entity.Livre;
import com.tech.infrastructure.local.db.LivreRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
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

        //données
        Livre livre = Livre.builder()
                .titre("Le vent")
                .auteur(auteur)
                .categorie("nature")
                .disponible(true)
                .isbn("3423565434")
                .build();
        livreRepository.save(livre);

        //Quand
        boolean res = livreRepository.existsByAuteur(auteur);

        //Alors
        assertThat(res).isTrue();

    }

    @Test
    void verifierSiLaCategorieDuLivreEst(){

        String categorie="science";
        //donnée
        Livre livre = Livre.builder()
                .titre("Le quotidien")
                .auteur("Luis mendez")
                .categorie(categorie)
                .disponible(true)
                .isbn("3423561424")
                .build();
        Livre lv = livreRepository.save(livre);

         //Quand
        String res = lv.getCategorie();

        //Alors
        assertThat(res).isEqualTo(categorie);

    }

    @Test
    void verifierSiLeLivreExistParIsbn(){
        String isbn="9345245678";

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

}
