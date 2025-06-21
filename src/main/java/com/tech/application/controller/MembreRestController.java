package com.tech.application.controller;


import com.tech.domain.entity.PageableDTO;
import com.tech.domain.membre.entity.Membre;
import com.tech.domain.membre.entity.MembreDto;
import com.tech.domain.membre.port.MembreDomain;
import com.tech.infrastructure.utils.OperationResult;
import com.tech.infrastructure.utils.StringHelper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping(value ="/api/membres")
public class MembreRestController {

    @Autowired
    private MembreDomain membreDomain;

    @GetMapping("/demo")
    public String demo() {
        return "hello";
    }

    @GetMapping("")
    public ResponseEntity<Page<Membre>> listeMembres(@RequestBody @Valid PageableDTO pageableDto) {
        return new ResponseEntity<>(membreDomain.findPaginated(pageableDto), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Membre> enregisterMembres(@RequestBody @Valid MembreDto membreDto) {
        return new ResponseEntity<>(membreDomain.save(membreDto), HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<OperationResult<Membre>> modifierMembres(
            @PathVariable() String id,
            @RequestBody  MembreDto membreDto
    ) {
        String idS = StringHelper.isLong(id);
        Optional<Membre> opt = membreDomain.findById(Long.parseLong(idS));
        Membre data = new Membre();
        HashMap<String, String> errors = new HashMap<>();

        if (opt.isPresent()) {

            Membre membre = opt.get();
            membre.setNom(membreDto.getNom());
            membre.setEmail(membreDto.getEmail());
            membre.setDateMembership(membreDto.getDateMembership());
            membre.setStatut(membreDto.getStatut());

            data = membreDomain.create(membre);

        } else {
            errors.put("membre", "Membre introuvable");
            data=null;
        }

        return new ResponseEntity<>(new OperationResult<>(data, errors), HttpStatus.OK);
    }

}
