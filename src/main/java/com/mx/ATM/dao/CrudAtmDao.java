package com.mx.ATM.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mx.ATM.domain.Atm;

@Repository
public interface CrudAtmDao extends CrudRepository<Atm, Integer>{


	

}
