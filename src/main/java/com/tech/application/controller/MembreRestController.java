package com.tech.application.controller;


import com.tech.domain.entity.PageableDTO;
import com.tech.domain.membre.entity.Membre;
import com.tech.domain.membre.entity.MembreDto;
import com.tech.domain.membre.port.MembreDomain;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value ="/membres")
public class MembreRestController {

    @Autowired
    private MembreDomain membreDomain;

    @GetMapping("")
    public ResponseEntity<Page<Membre>> listeMembres   (@RequestBody @Valid PageableDTO pageableDto){
        return new ResponseEntity<>(membreDomain.findPaginated(pageableDto), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Membre> enregisterMembres   (@RequestBody @Valid MembreDto membreDto){
        return new ResponseEntity<>(membreDomain.save(membreDto), HttpStatus.OK);
    }

}
