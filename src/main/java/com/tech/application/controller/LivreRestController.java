package com.tech.application.controller;

import com.tech.domain.livre.entity.Livre;
import com.tech.domain.livre.entity.LivreDTO;
import com.tech.domain.livre.port.LivreDomain;
import com.tech.infrastructure.utils.OperationResult;
import com.tech.infrastructure.utils.StringHelper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/api/livres")
public class LivreRestController {

    @Autowired
    private LivreDomain livreDomain;

    @GetMapping("")
    public ResponseEntity<List<Livre>> listeLivres (
         @Valid @RequestBody LivreDTO livreDTO
    ){

        ArrayList<Livre> lists = livreDomain.search(livreDTO.getAuteur(),livreDTO.getCategorie(),livreDTO.isDisponible());
        return new ResponseEntity<>(lists, HttpStatus.OK);

    }


    @PostMapping("")
    public ResponseEntity<OperationResult<Livre>> enregistreLivres (
            @Valid @RequestBody LivreDTO livreDTO
    ){

        HashMap<String,String> errors = new HashMap<>();
        Livre data = null;

        String isbn = livreDTO.getIsbn();
        String id = StringHelper.isLong(livreDTO.getId());

        ArrayList<Livre> optLiv = livreDomain.findAllByIsbnAndIdNot(isbn,Long.parseLong(id));

        if(!optLiv.isEmpty()){
            errors.put("isbn","L'isbn du livre a déjà été utilisé");
        }

        if(errors.isEmpty()){
            data = livreDomain.create(livreDTO);
        }

        return new ResponseEntity<>(new OperationResult<>(data,errors),HttpStatus.OK);
    }




}
