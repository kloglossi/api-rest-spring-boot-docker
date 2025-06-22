package com.tech.integration;

import com.tech.domain.livre.entity.Livre;
import com.tech.domain.livre.port.LivreDomain;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class LivreControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private LivreDomain livreDomain;

    @Test
    public void testCreerLivre() throws Exception {

        int min = 10000;
        int max = 100000;
        int randomNumber = (int)(Math.random() * (max - min + 1)) + min;
        String isbn ="23400045"+randomNumber;

        String auteur ="jean";
        String titre="La vie";
        String categorie="culture";
        String disponible="true";

        String livreJson = String.format("{\"auteur\":\"%s\",\"titre\":\"%s\"," +
                "\"isbn\": \"%s\" ,\"categorie\":\"%s\",\"disponible\":\"%s\"}",auteur,titre,isbn,categorie,disponible);

        mockMvc.perform(post("/api/livres")
                .contentType(MediaType.APPLICATION_JSON)
                .content(livreJson)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isSuccess").value(true));

    }

    @Test
    public void testRecupererListeLivre() throws Exception {

        String auteur ="jean";
        String categorie="culture";
        String disponible="true";

        String livreJson = String.format("{\"auteur\":\"%s\",\"categorie\":\"%s\",\"disponible\":\"%s\"}",auteur,categorie,disponible);


        mockMvc.perform(get("/api/livres")
                                .contentType(MediaType.APPLICATION_JSON)
                        .content(livreJson)
                )
                .andExpect(status().isOk());
        //.andExpect(jsonPath("$.isSuccess").value(true));

    }

}
