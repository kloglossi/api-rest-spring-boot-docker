package com.tech.domain.pret.port;

import com.tech.domain.pret.entity.Pret;
import com.tech.domain.pret.entity.PretDTO;

import java.util.ArrayList;
import java.util.List;

public interface IManagePret {

    Long count();

    Pret empruter(PretDTO pretDTO);

    Pret save(Pret pret);

    List<Pret> findAll();

}
