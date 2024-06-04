package com.mx.ATM.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mx.ATM.domain.Atm;

@Service
public interface IAtm {
	
	
	public List<Atm> listAvailable();
	
	public boolean retiro(double monto);
	
	public boolean reload();

}
