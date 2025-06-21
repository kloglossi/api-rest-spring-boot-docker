package com.tech.domain.pret.worker;

import com.tech.domain.pret.entity.Pret;
import com.tech.domain.pret.port.PretDomain;
import com.tech.infrastructure.local.db.PretRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PretWorker implements PretDomain {

    @Autowired
    PretRepository pretRepository;

    @Override
    public Long count() {
        return null;
    }

    @Override
    public Pret empruter(Long membreId, Long livre) {
        return null;
    }

    @Override
    public ArrayList<Pret> findAll() {
        return null;
    }


}
