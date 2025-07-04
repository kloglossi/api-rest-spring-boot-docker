package com.tech.domain.pret.worker;

import com.tech.domain.pret.entity.Pret;
import com.tech.domain.pret.entity.PretDTO;
import com.tech.domain.pret.port.PretDomain;
import com.tech.infrastructure.local.db.PretRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.tech.domain.entity.StateData.BORROW;

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
    public List<Pret> findAllByIdAndStatutIsNotAndDateRetourIsNotNull(Long id, String statut) {
        return pretRepository.findAllByIdAndStatutIsNotAndDateRetourIsNotNull(
                id, statut);
    }

    @Override
    public Long countAllByMembreIdAndStatut(Long membreId, String statut) {
        return pretRepository.countAllByMembreIdAndStatut(membreId, statut);
    }

    @Override
    public List<Pret> findAllByStatutAndDateRetourIsNull(String statut) {
        return pretRepository.findAllByStatutAndDateRetourIsNull(statut);
    }

    @Override
    public List<Pret> findAllByStatutAndDateRetourIsNotNull(String statut) {
        return pretRepository.findAllByStatutAndDateRetourIsNotNull(statut);
    }

    @Override
    public List<Pret> findAllById(Long id) {
        return pretRepository.findAllById(id);
    }

    @Override
    public List<Pret> findAllByMembreId(Long membreId) {
        return pretRepository.findAllByMembreId(membreId);
    }

    @Override
    public List<Pret> findAllByMembreIdAndLivreId(Long membreId, Long livreId) {
        return pretRepository.findAllByMembreIdAndLivreId(membreId, livreId);
    }

    @Override
    public List<Pret> findAllByMembreIdAndLivreIdAndStatut(Long membreId, Long livreId, String statut) {
        return pretRepository.findAllByMembreIdAndLivreIdAndStatut(membreId, livreId, statut);
    }

    @Override
    public Long countAllByStatutAndDateRetourIsNotNull(String statut) {
        return pretRepository.countAllByStatutAndDateRetourIsNotNull(statut);
    }

    @Override
    public Long countAllByStatutAndDateRetourIsNull(String statut) {
        return pretRepository.countAllByStatutAndDateRetourIsNull(statut);
    }

    @Override
    public Long countAllByLivreId(Long livreId) {
        return pretRepository.countAllByLivreId(livreId);
    }


}
