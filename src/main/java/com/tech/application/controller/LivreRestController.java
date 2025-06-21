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
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/livres")
public class LivreRestController {

    @Autowired
    private LivreDomain livreDomain;

    @GetMapping("")
    public ResponseEntity<List<Livre>> listeLivres (
          @RequestBody LivreDTO livreDTO
    ){
        String dis = StringHelper.isBool(livreDTO.getDisponible());
        String auteur = (livreDTO.getAuteur()==null) ? "" : livreDTO.getAuteur();
        String categorie = (livreDTO.getCategorie()==null) ? "" : livreDTO.getCategorie();

        ArrayList<Livre> lists = livreDomain.searchOr(auteur,categorie,Boolean.parseBoolean(dis));
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

    @PutMapping("/{id}")
    public ResponseEntity<OperationResult<Livre>> modifierLivre(
            @PathVariable String id,@RequestBody LivreDTO livreDTO
    ){

        Livre data = null;
        HashMap<String, String> errors = new HashMap<>();

        String auteur = (livreDTO.getAuteur()==null) ? "" : livreDTO.getAuteur();
        String titre = (livreDTO.getTitre()==null) ? "" : livreDTO.getTitre();
        String dis = (livreDTO.getDisponible()==null) ? "" : livreDTO.getDisponible();
        String categorie = (livreDTO.getCategorie()==null) ? "" : livreDTO.getCategorie();
        String isbn = (livreDTO.getIsbn()==null) ? "" : livreDTO.getIsbn();

        String idS = StringHelper.isLong(id);
        Optional<Livre> opt = livreDomain.findById(Long.parseLong(idS));

        if(opt.isEmpty()){
            errors.put("livre","Le livre est introuvable");
        }

        if(errors.isEmpty()){

            Livre livre = opt.get();

            if(!auteur.isEmpty()){
                livre.setAuteur(livreDTO.getAuteur());
            }

            if(!titre.isEmpty()){
                livre.setTitre(livreDTO.getTitre());
            }

            if(!dis.isEmpty()){
                boolean disB = StringHelper.isBoolPlus(dis);
                if(disB){
                    livre.setDisponible(Boolean.parseBoolean(dis));
                }else errors.put("disponible","Disponibilité incorrecte");
            }

            if(!categorie.isEmpty()){
                livre.setCategorie(livreDTO.getCategorie());
            }

            if(!isbn.isEmpty()){
                if(isbn.length()<10){
                    errors.put("isbn","L'isbn doit être supérieur à 9 caractères");
                }else livre.setIsbn(livreDTO.getIsbn());
            }

            if(errors.isEmpty()){
                data = livreDomain.save(livre);
            }

        }
        return new ResponseEntity<>(new OperationResult<>(data,errors),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OperationResult<Livre>> recupereLivre(
            @PathVariable String id
    ){

        HashMap<String,String> errors = new HashMap<>();
        Livre data = null;

        String idS = StringHelper.isLong(id);
        Optional<Livre> opt = livreDomain.findById(Long.parseLong(idS));

        if(opt.isEmpty()){
            errors.put("livre","Le livre est introuvale");
        }

        if(errors.isEmpty()){
            data = opt.get();
        }

        return new ResponseEntity<>(new OperationResult<>(data,errors),HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<OperationResult<Boolean>> supprimeLivre(
            @PathVariable String id
    ){

        HashMap<String,String> errors = new HashMap<>();
        boolean data = false;

        String idS = StringHelper.isLong(id);
        Optional<Livre> opt = livreDomain.findById(Long.parseLong(idS));

        if(opt.isEmpty()){
            errors.put("livre","Le livre est introuvale");
        }

        if(errors.isEmpty()){
            data = livreDomain.removeById(Long.parseLong(idS));
        }

        return new ResponseEntity<>(new OperationResult<>(data,errors),HttpStatus.OK);
    }





}
