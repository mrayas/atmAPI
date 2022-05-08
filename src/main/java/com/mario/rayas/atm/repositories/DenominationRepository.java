package com.mario.rayas.atm.repositories;

import java.util.ArrayList;

import com.mario.rayas.atm.models.DenominationModel;

import org.springframework.data.domain.Sort;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/*
Desarrolldo por Mario Sergio Rayas Chávez
Correo mrayas@gmail.com
Fecha Mayo 8, 2022
Para manejar los datos en MySQL
*/

@Repository
public interface DenominationRepository extends CrudRepository <DenominationModel,Double> {
    public abstract ArrayList<DenominationModel> findByType(String type);
    public abstract ArrayList<DenominationModel> findAllWithCustomOrderBy(Sort sort);
    
}
