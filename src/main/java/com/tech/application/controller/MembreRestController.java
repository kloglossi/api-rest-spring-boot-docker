package com.tech.application.controller;


import com.tech.domain.entity.PageableDTO;
import com.tech.domain.membre.entity.Membre;
import com.tech.domain.membre.entity.MembreDto;
import com.tech.domain.membre.port.MembreDomain;
import com.tech.domain.pret.entity.Pret;
import com.tech.domain.pret.port.PretDomain;
import com.tech.infrastructure.utils.OperationResult;
import com.tech.infrastructure.utils.StringHelper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value ="/api/membres")
public class MembreRestController {

    @Autowired
    private MembreDomain membreDomain;

    @Autowired
    private PretDomain pretDomain;

    @GetMapping("")
    public ResponseEntity<Page<Membre>> listeMembres(@Valid @RequestBody PageableDTO pageableDto) {
        return new ResponseEntity<>(membreDomain.findPaginated(pageableDto), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<OperationResult<Membre>> enregisterMembres(@Valid @RequestBody MembreDto membreDto) {

        HashMap<String,String> errors = new HashMap<>();
        Membre data = null;

        String idS = StringHelper.isLong(membreDto.getId());
        String dateL = StringHelper.isLocalDate(membreDto.getDateMembership());
        String email = membreDto.getEmail();

        String optDate = (dateL.equals("")) ? errors.put("date","Format de date incorrete") : dateL;
        Optional<Membre> opt = membreDomain.findByEmailAndIdNot(email,Long.parseLong(idS));

        if(opt.isPresent()){
            errors.put("email","L'email a déjà été utilisé");
        }

        if(errors.isEmpty()){
            data=membreDomain.save(membreDto);
        }

        return new ResponseEntity<>(new OperationResult<>(data,errors), HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<OperationResult<Membre>> modifierMembres(
            @PathVariable("id") String id,
            @RequestBody  MembreDto membreDto
    ) {
        Membre data = null;
        HashMap<String, String> errors = new HashMap<>();

        String idS = StringHelper.isLong(id);
        Optional<Membre> opt = membreDomain.findById(Long.parseLong(idS));

        String email = (membreDto.getEmail()==null) ? "" : membreDto.getEmail();
        String nom = (membreDto.getNom()==null) ? "" : membreDto.getNom();
        String statut = (membreDto.getStatut()==null) ? "" : membreDto.getStatut();
        String dateMembership = (membreDto.getDateMembership()==null) ? "" : membreDto.getDateMembership();

        if(opt.isEmpty()){
            errors.put("membre","Le membre est introuvable");
        }

        if(errors.isEmpty()){

            Membre membre = opt.get();

            if (!email.isEmpty()) {
                Optional<Membre> optEmail = membreDomain.findByEmailAndIdNot(email,Long.parseLong(idS));

                if(optEmail.isEmpty()){
                    membre.setEmail(membreDto.getEmail());
                }else errors.put("email","L'email a déjà été utilisé");

            }

            if (!nom.isEmpty()) membre.setNom(membreDto.getNom());

            if (!statut.isEmpty()) membre.setStatut(membreDto.getStatut());

            if(!dateMembership.isEmpty()){

                String dateL = StringHelper.isLocalDate(membreDto.getDateMembership());
                if(!dateL.equals("")){
                    membre.setDateMembership(
                            LocalDate.parse(membreDto.getDateMembership(), DateTimeFormatter.ofPattern(dateL))
                    );
                }else errors.put("date","Format de date incorrete");

            }

            if(errors.isEmpty()){
                data = membreDomain.create(membre);
            }

        }

        return new ResponseEntity<>(new OperationResult<>(data, errors), HttpStatus.OK);
    }

    @GetMapping("/{id}/prets")
    public ResponseEntity<OperationResult<List<Pret>>> historiquePret(
            @PathVariable String id
    ){

        List<Pret> data =null;
        HashMap<String, String> errors = new HashMap<>();

        String idS = StringHelper.isLong(id);
        List<Pret> list = pretDomain.findAllByMembreId(Long.parseLong(idS));

        if(list.isEmpty()){
            errors.put("membre","Cet membre n'a effectué aucun emprunt de livre");
        }

        if(errors.isEmpty()){
            data = list;
        }

        return new ResponseEntity<>(new OperationResult<>(data,errors),HttpStatus.OK);
    }

}
