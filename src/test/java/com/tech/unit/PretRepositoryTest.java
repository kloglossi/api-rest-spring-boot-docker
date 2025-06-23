package com.tech.unit;

import com.tech.BiblioDevApplication;
import com.tech.domain.livre.entity.Livre;
import com.tech.domain.membre.entity.Membre;
import com.tech.domain.pret.entity.Pret;
import com.tech.infrastructure.local.db.LivreRepository;
import com.tech.infrastructure.local.db.MembreRepository;
import com.tech.infrastructure.local.db.PretRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDate;
import java.util.Optional;

import static com.tech.domain.entity.StateData.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@ContextConfiguration(classes = BiblioDevApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PretRepositoryTest {

    @Autowired
    private PretRepository pretRepository;

    @Autowired
    private MembreRepository membreRepository;

    @Autowired
    LivreRepository livreRepository;

    @AfterEach
    void tearDown(){
        pretRepository.deleteAll();
    }

    @Test
    void emprunterEtRetournerLivre(){

        int min = 100000;
        int max = 1000000;
        int randomNumber = (int)(Math.random() * (max - min + 1)) + min;
        String email ="ricardo"+randomNumber+"@gmail.com";

        String isbn ="99400045"+randomNumber;

        //données (emprunter)
        Livre livre = Livre.builder()
                .titre("Le vent")
                .auteur("Jean pierre")
                .categorie("nature")
                .disponible(true)
                .isbn(isbn)
                .build();
        livreRepository.save(livre);

        Membre membre = Membre.builder()
                .email(email)
                .nom("helo")
                .dateMembership(LocalDate.parse("2025-04-11"))
                .statut(ENABLED)
                .build();
        membreRepository.save(membre);


        Pret pret = Pret.builder()
                .livreId(livre.getId())
                .membreId(membre.getId())
                .datePret(LocalDate.parse("2025-05-01"))
                .statut(BORROW)
                .build();

       Pret p= pretRepository.save(pret);

       //Quand (emprunter)
        Optional<Pret> optionalPret = pretRepository.findById(p.getId());

        //Alors (emprunter)
        assertThat(optionalPret).isPresent();

        //donnée(retour livre)
        p.setDateRetour(LocalDate.parse("2025-05-10"));
        p.setStatut(RETURNED);

        Pret retour =pretRepository.save(p);

        //Quand (retour)
        String statut = retour.getStatut();

        //Alors
        assertThat(statut).isEqualTo(RETURNED);

    }

}
