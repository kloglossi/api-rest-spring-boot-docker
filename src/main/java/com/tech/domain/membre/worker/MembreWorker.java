package com.tech.domain.membre.worker;


import com.tech.domain.entity.PageableDTO;
import com.tech.domain.membre.entity.Membre;
import com.tech.domain.membre.entity.MembreDto;
import com.tech.domain.membre.port.MembreDomain;
import com.tech.infrastructure.local.db.MembreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class MembreWorker implements MembreDomain {

    @Autowired
    private MembreRepository membreRepository;


    @Override
    public Page<Membre> findPaginated(PageableDTO pagea) {

        String sortD = pagea.getSortDir();

        Sort sort = sortD.equals(Sort.Direction.ASC.name()) ? Sort.by(pagea.getSortField()).ascending() : Sort.by(pagea.getSortField()).descending();
        Pageable pageable = PageRequest.of(pagea.getPageNo() - 1, pagea.getPageSize(), sort);
        return membreRepository.findAll(pageable);

    }

    @Override
    public Long count() {
        return membreRepository.count();
    }



    @Override
    public Membre save(MembreDto membreDto) {

        Membre save = new Membre();

        save.setNom(membreDto.getNom());
        save.setEmail(membreDto.getEmail());
        save.setDateMembership(membreDto.getDateMembership());
        save.setStatut(membreDto.getStatut());

        return membreRepository.save(save);
    }

    @Override
    public Optional<Membre> findById(Long id) {
        return Optional.empty();
    }
}
