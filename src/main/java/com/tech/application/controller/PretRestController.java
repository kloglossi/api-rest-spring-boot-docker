package com.tech.application.controller;

import com.tech.domain.amende.enity.Amende;
import com.tech.domain.amende.port.AmendeDomain;
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
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;

import static com.tech.domain.entity.StateData.*;

@RestController
@RequestMapping("/api/prets")
public class PretRestController {

    @Autowired
    PretDomain pretDomain;

    @Autowired
    LivreDomain livreDomain;

    @Autowired
    MembreDomain membreDomain;

    @Autowired
    AmendeDomain amendeDomain;

    @PostMapping("")
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
               errors.put("pret","Malheureusement vous avez déjà trois emprunts actifs, vous ne pouvez plus emprunter de livre");
           }

        }

        String dateP = StringHelper.isLocalDate(pretDTO.getDatePret());
        String dateF = (dateP.equals("")) ? errors.put("datePret","Le format de la date de prêt est incorrecte") : dateP;

        if(errors.isEmpty()){

            data = pretDomain.empruter(pretDTO);

        }

        return new ResponseEntity<>(new OperationResult<>(data,errors), HttpStatus.OK);
    }

    @PutMapping("/{id}/retour")
    public ResponseEntity<OperationResult<Pret>> retourLivre(
            @PathVariable String id,
            @RequestBody PretDTO pretDTO
    ){

        Pret data = null;
        HashMap<String, String> errors = new HashMap<>();
        Long nbreJoursPenalite = 0L;
        String statutFinal="";

        String ids = StringHelper.isLong(id);
        Optional<Pret> optionalPret = pretDomain.findById(Long.parseLong(ids));

        if(optionalPret.isEmpty()){
            errors.put("pret","Le pret est introuvable");
        }

        String dateL = (pretDTO.getDateRetour()==null) ? "" : pretDTO.getDateRetour();
        String formatDate = StringHelper.isLocalDate(dateL);

        if(errors.isEmpty()){

            if(formatDate.equals("")){
                errors.put("dateRetour","La date de retour est incorrecte");
            }else {

                Pret pret = optionalPret.get();
                LocalDate dateRetour = LocalDate.parse(dateL, DateTimeFormatter.ofPattern(formatDate));
                LocalDate datePret = pret.getDatePret();
                LocalDate penalite = datePret.plusDays(14);

                if(datePret.isAfter(dateRetour) || datePret.isEqual(dateRetour)){
                    errors.put("date","la date retour ne doit pas être inférieur ou égale à la date de prêt");
                }else{

                    if(dateRetour.isAfter(penalite)){
                        statutFinal = LATE_DELIVERY;
                        nbreJoursPenalite = ChronoUnit.DAYS.between(penalite,dateRetour);
                        System.out.println("nb jours penalité : "+nbreJoursPenalite);
                    }else {
                        statutFinal =RETURNED;
                    }

                }

            }

            if(errors.isEmpty()){

                Pret pret = optionalPret.get();
                pret.setDateRetour(LocalDate.parse(dateL,DateTimeFormatter.ofPattern(formatDate)));
                pret.setStatut(statutFinal);
                data = pretDomain.save(pret);

                if (data.getId()!=-1L && nbreJoursPenalite>0){

                    double cout = 500.0;
                    Amende amende = Amende.builder()
                            .pretId(data.getId())
                            .coutParJour(cout)
                            .nombreJoursRetard(nbreJoursPenalite.intValue())
                            .coutTotal(nbreJoursPenalite.doubleValue()*cout)
                            .build();
                    amendeDomain.save(amende);
                }

            }

        }

        return new ResponseEntity<>(new OperationResult<>(data,errors),HttpStatus.OK);
    }


}
