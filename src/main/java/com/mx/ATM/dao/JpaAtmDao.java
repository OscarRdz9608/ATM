package com.mx.ATM.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mx.ATM.domain.Atm;


@Repository
public interface JpaAtmDao extends JpaRepository<Atm, Integer> {

	@Query(nativeQuery = true, value = "select sum(denominacion * cantidad) saldo from ATM")
	public double getSaldo();	

	
	@Query(nativeQuery = true, value = " execute reload_atm")
	public void getReload();	


	
}
