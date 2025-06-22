package com.tech.application.controller;

import com.tech.domain.livre.port.LivreDomain;
import com.tech.domain.membre.entity.Membre;
import com.tech.domain.membre.port.MembreDomain;
import com.tech.domain.pret.port.PretDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

import static com.tech.domain.entity.StateData.*;

@RestController
@RequestMapping(value = "/api/statistiques")
public class StatistiqueRestController {

    @Autowired
    PretDomain pretDomain;

    @Autowired
    MembreDomain membreDomain;

    @Autowired
    LivreDomain livreDomain;


    @GetMapping("")
    public ResponseEntity<HashMap<String,String>> statistiques(){

        HashMap<String,String> data =new HashMap<>();

        Long totalLivre = livreDomain.count();
        Long livresDis =livreDomain.countAllByDisponible(true);
        Long livresIndis = livreDomain.countAllByDisponible(false);
        Long pretsActifs = pretDomain.countAllByStatutAndDateRetourIsNull(BORROW);
        Long pretsRetards = pretDomain.countAllByStatutAndDateRetourIsNotNull(LATE_DELIVERY);
        Long retourPretsNormal = pretDomain.countAllByStatutAndDateRetourIsNotNull(RETURNED);
        Long totalMembres = membreDomain.count();
        Long totalMembresActif = membreDomain.countAllByStatut(ENABLED);
        Long totalMembresInactif = membreDomain.countAllByStatut(DISABLED);
        Long totalPrets = pretDomain.count();

        data.put("total-livres",totalLivre.toString());
        data.put("livres-disponible",livresDis.toString());
        data.put("livres-indisponible",livresIndis.toString());
        data.put("total-prets",totalPrets.toString());
        data.put("prets-actifs",pretsActifs.toString());
        data.put("prets-retards",pretsRetards.toString());
        data.put("retour-prets-normal",retourPretsNormal.toString());
        data.put("total-membres",totalMembres.toString());
        data.put("total-membres-actifs",totalMembresActif.toString());
        data.put("total-membres-inactifs",totalMembresInactif.toString());

        return new ResponseEntity<>(data, HttpStatus.OK);
    }

}
