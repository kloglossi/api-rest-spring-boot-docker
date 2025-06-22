package com.tech.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.tech.domain.entity.StateData.ENABLED;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MembreControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCreerMembre() throws Exception {

        int min = 100000;
        int max = 1000000;
        int randomNumber = (int)(Math.random() * (max - min + 1)) + min;
        String email ="jean"+randomNumber+"@gmail.com";

        String nom ="jean";
        String dateMembership="2025-02-05";

        String membreJson = String.format("{\"nom\":\"%s\",\"email\":\"%s\"," +
                "\"dateMembership\": \"%s\" ,\"statut\":\"%s\"}",nom,email,dateMembership, ENABLED);

        mockMvc.perform(post("/api/membres")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(membreJson)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isSuccess").value(true));

    }

    @Test
    public void testRecupererListeMembre() throws Exception {

        String pageNo ="1";
        String itemsByPage="5";
        String sortDir="ASC";

        String livreJson = String.format("{\"pageNo\":\"%s\",\"itemsByPage\":\"%s\",\"sortDir\":\"%s\"}",pageNo,itemsByPage,sortDir);

        mockMvc.perform(get("/api/membres")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(livreJson)
                )
                .andExpect(status().isOk());
        //.andExpect(jsonPath("$.isSuccess").value(true));

    }

}
