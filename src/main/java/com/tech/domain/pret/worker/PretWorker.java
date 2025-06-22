package com.tech.domain.pret.worker;

import com.tech.domain.pret.entity.Pret;
import com.tech.domain.pret.entity.PretDTO;
import com.tech.domain.pret.port.PretDomain;
import com.tech.infrastructure.local.db.PretRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.tech.domain.entity.StateData.BORROW;
import static com.tech.domain.entity.StateData.ENABLED;

@Service
public class PretWorker implements PretDomain {

    @Autowired
    PretRepository pretRepository;

    @Override
    public Long count() {
        return pretRepository.count();
    }

    @Override
    public Pret empruter(PretDTO pretDTO) {
        Pret pret = Pret.builder()
                .datePret(LocalDate.parse(pretDTO.getDatePret()))
                //.dateRetour(LocalDate.parse(pretDTO.getDateRetour()))
                .membreId(Long.parseLong(pretDTO.getMembreId()))
                .livreId(Long.parseLong(pretDTO.getLivreId()))
                .statut(BORROW)
                .build();
        return pretRepository.save(pret);
    }

    @Override
    public Pret save(Pret pret) {
        return pretRepository.save(pret);
    }

    @Override
    public List<Pret> findAll() {
        return pretRepository.findAll();
    }

    @Override
    public Optional<Pret> findById(Long id) {
        return pretRepository.findById(id);
    }

    @Override
    public Long countAllByMembreIdAndStatut(Long membreId, String statut) {
        return pretRepository.countAllByMembreIdAndStatut(membreId, statut);
    }


}
