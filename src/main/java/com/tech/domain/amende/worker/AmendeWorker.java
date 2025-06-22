package com.tech.domain.amende.worker;

import com.tech.domain.amende.enity.Amende;
import com.tech.domain.amende.port.AmendeDomain;
import com.tech.infrastructure.local.db.AmendeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AmendeWorker implements AmendeDomain {

    @Autowired
    private AmendeRepository amendeRepository;

    @Override
    public Long count() {
        return amendeRepository.count();
    }

    @Override
    public Amende save(Amende amende) {

        return amendeRepository.save(amende);
    }

    @Override
    public List<Amende> findAllByPretId(Long pretId) {
        return amendeRepository.findAllByPretId(pretId);
    }

    @Override
    public List<Amende> findAll() {
        return amendeRepository.findAll();
    }

    @Override
    public Optional<Amende> findById(Long id) {
        return amendeRepository.findById(id);
    }
}
