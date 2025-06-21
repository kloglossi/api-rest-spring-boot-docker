package com.tech.application.controller;

import com.tech.domain.entity.StateData;
import com.tech.domain.livre.entity.Livre;
import com.tech.domain.livre.port.LivreDomain;
import com.tech.domain.membre.entity.Membre;
import com.tech.domain.membre.port.MembreDomain;
import com.tech.domain.pret.entity.Pret;
import com.tech.domain.pret.entity.PretDTO;
import com.tech.domain.pret.port.PretDomain;
import com.tech.infrastructure.utils.OperationResult;
import com.tech.infrastructure.utils.StringHelper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;

import static com.tech.domain.entity.StateData.BORROW;
import static com.tech.domain.entity.StateData.ENABLED;

@RestController
@RequestMapping("/api/prets")
public class PretRestController {

    @Autowired
    PretDomain pretDomain;

    @Autowired
    LivreDomain livreDomain;

    @Autowired
    MembreDomain membreDomain;

    @PostMapping
    public ResponseEntity<OperationResult<Pret>> emprunterLivre(
            @Valid @RequestBody PretDTO pretDTO
    ){

        Pret data = null;
        HashMap<String, String> errors = new HashMap<>();

        String membreId = StringHelper.isLong(pretDTO.getMembreId());
        String livreId = StringHelper.isLong(pretDTO.getLivreId());

        Optional<Membre> optlMembre = membreDomain.findById(Long.parseLong(membreId));
        Optional<Livre> optionalLivre = livreDomain.findById(Long.parseLong(livreId));

        if(optionalLivre.isEmpty()){
            errors.put("livre","Le livre est introuvable");
        }else {

            Livre livre = optionalLivre.get();

            if(!livre.isDisponible()){
                errors.put("livre","Le livre n'est pas disponible");
            }

        }

        if(optlMembre.isEmpty()){
            errors.put("membre","Le membre est introuvable");
        }else {

           Membre membre = optlMembre.get();
           Long countPret = pretDomain.countAllByMembreIdAndStatut(Long.parseLong(membreId),BORROW);

           if(!Objects.equals(membre.getStatut(), ENABLED)){
               errors.put("membre","Le membre n'est pas actif, pour qu'il le soit il faut que son statut soit égale à ENABLED");
           }

           if (countPret>=3){
               errors.put("pret","Malheureusement vous avez déjà trois emprunts actifs, nous ne pouvez pas emprunter de livre");
           }

        }

        if(errors.isEmpty()){

        }

        return new ResponseEntity<>(new OperationResult<>(data,errors), HttpStatus.OK);
    }

}
