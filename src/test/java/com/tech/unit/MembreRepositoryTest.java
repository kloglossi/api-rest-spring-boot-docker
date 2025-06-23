package com.tech.unit;

import com.tech.BiblioDevApplication;
import com.tech.domain.membre.entity.Membre;
import com.tech.infrastructure.local.db.MembreRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.tech.domain.entity.StateData.ENABLED;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@DataJpaTest
@ContextConfiguration(classes = BiblioDevApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MembreRepositoryTest {


    @Autowired
    MembreRepository membreRepository;

    @AfterEach
    void tearDown(){
        membreRepository.deleteAll();
    }

    @Test
    void verifierSiLeMembreExistParEmail(){

        int min = 100000;
        int max = 1000000;
        int randomNumber = (int)(Math.random() * (max - min + 1)) + min;
        String email ="jeanclaude"+randomNumber+"@gmail.com";

        //Donnée
        Membre membre = Membre.builder()
                .email(email)
                .nom("jean")
                .dateMembership(LocalDate.parse("2025-04-11"))
                .statut(ENABLED)
                .build();
        membreRepository.save(membre);

        //Quand
        boolean res = membreRepository.existsByEmail(membre.getEmail());

        //Alors
        assertThat(res).isTrue();

    }

    @Test
    void verifierSiLeMembreEstActif(){

        int min = 100000;
        int max = 1000000;
        int randomNumber = (int)(Math.random() * (max - min + 1)) + min;
        String email ="crismoff"+randomNumber+"@gmail.com";

        //donnée
        Membre membre = Membre.builder()
                .email(email)
                 .nom("clasusse")
                .dateMembership(LocalDate.parse("2025-05-11"))
                .statut(ENABLED)
                .build();

        membreRepository.save(membre);

        //Quand
        String res = membre.getStatut();

        //Alors
        assertThat(res).isEqualTo(ENABLED);

    }

    @Test
    void rechercherMembreInexistantParEmail(){

        //Donnée
        String email = "dox_er.@iej@.fx##";

        //Quand
        Optional<Membre> opt = membreRepository.findByEmail(email);

        //Alors
        assertThat(opt).isEmpty();
    }

    @Test
    void recupererUnMembreInexistantParId(){

        //donnée
        Long id =-1L;

        //Quand
        Optional<Membre> opt = membreRepository.findById(id);

        //Alors
        assertThat(opt).isEmpty();
    }




}
