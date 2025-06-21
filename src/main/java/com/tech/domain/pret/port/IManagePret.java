package com.tech.domain.pret.port;

import com.tech.domain.pret.entity.Pret;

import java.util.ArrayList;

public interface IManagePret {

    Long count();

    Pret empruter(Long membreId,Long livre);

    ArrayList<Pret> findAll();

}
