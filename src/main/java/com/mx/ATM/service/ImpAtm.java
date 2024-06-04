package com.mx.ATM.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.mx.ATM.dao.CrudAtmDao;
import com.mx.ATM.dao.JpaAtmDao;
import com.mx.ATM.domain.Atm;
import com.mx.ATM.domain.montoDTO;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Service
public class ImpAtm implements IAtm {

	@Autowired CrudAtmDao daoCrud;
	@Autowired JpaAtmDao  daoJpa;
	@Autowired
    private JdbcTemplate jdbcTemplate;
	

	@Override
	public List<Atm> listAvailable() {
		// TODO Auto-generated method stub
		List<Atm> available = (List<Atm>) daoCrud.findAll();
		if (!available.isEmpty()) {
			return available;
		} else {
			return null;
		}
	}

	@Override
	public boolean retiro(double monto) {
		// TODO Auto-generated method stub
		
		
		
		
		
		return false;
	}

    @Transactional
	@Override
	public boolean reload() {
		// TODO Auto-generated method stub
		jdbcTemplate.execute("DROP TABLE ATM");
        jdbcTemplate.execute("CREATE TABLE ATM (" +
                "ID NUMBER NOT NULL PRIMARY KEY, " +
                "TIPO NVARCHAR2(10), " +
                "CANTIDAD NUMBER, " +
                "DENOMINACION NUMBER" +
                ")");

        String[] insertQueries = {
            "INSERT INTO ATM VALUES (1,'Billete',2,1000)",
            "INSERT INTO ATM VALUES (2,'Billete',5,500)",
            "INSERT INTO ATM VALUES (3,'Billete',10,200)",
            "INSERT INTO ATM VALUES (4,'Billete',20,100)",
            "INSERT INTO ATM VALUES (5,'Billete',30,50)",
            "INSERT INTO ATM VALUES (6,'Billete',40,20)",
            "INSERT INTO ATM VALUES (7,'Moneda',50,10)",
            "INSERT INTO ATM VALUES (8,'Moneda',100,5)",
            "INSERT INTO ATM VALUES (9,'Moneda',200,2)",
            "INSERT INTO ATM VALUES (10,'Moneda',300,1)",
            "INSERT INTO ATM VALUES (11,'Moneda',100,0.50)"
        };

        for (String query : insertQueries) {
            jdbcTemplate.execute(query);
        }
		
		return true;
	}
	
	
	public double getSaldo() {
		return daoJpa.getSaldo();
	}
	
public void descontarDinero() {
	double monto=2549.5;
	List<Atm> available = (List<Atm>) daoCrud.findAll();
	if (!available.isEmpty()) {
		
		for (Atm atm : available) {
			System.out.println(atm.getCantidad());
			System.out.println(atm.getDenominacion());
			
			System.out.println(atm.getCantidad()*atm.getDenominacion());
		}
			
	} else {
		
	}
}


@Transactional
@Modifying
public boolean distribuirDinero(double monto) {
	System.out.println("Monto "+ monto);
    List<Atm> atmList = daoJpa.findAll();


    for (Atm atm : atmList) {   	
        if (atm.getTipo().equals("Billete")) {
        
        	int numBilletes = (int) (monto / atm.getDenominacion());
            	if(numBilletes > atm.getCantidad()) {
            	System.out.println("Cantidad "+numBilletes+" Denominacion  "+ atm.getDenominacion());
            	numBilletes = atm.getCantidad();
           	}
            monto -= numBilletes * atm.getDenominacion();	
            int aux = atm.getCantidad();
            aux -= numBilletes; 
            atm.setCantidad(aux);
        	//System.out.println("monto "+monto);
      	//System.out.println("cantidad "+atm.getCantidad());
            daoJpa.save(atm);
                        
        	
        } else if (atm.getTipo().equals("Moneda")) {
        	int numMonedas = (int) (monto / atm.getDenominacion());
        	if(numMonedas > atm.getCantidad()) {
        	System.out.println("Cantidad "+numMonedas+" Denominacion  "+ atm.getDenominacion());
        	numMonedas = atm.getCantidad();
       	}
        monto -= numMonedas * atm.getDenominacion();	
        int aux = atm.getCantidad();
        aux -= numMonedas; 
        atm.setCantidad(aux);
        daoJpa.save(atm);

        }
    }

    return true;
    
}






@Transactional
@Modifying
public boolean distribuirDinero1(double monto) {
	
    System.out.println("Montoooo "+ monto);
    List<Atm> atmList = daoJpa.findAll();

    for (Atm atm : atmList) {       
        if (atm.getTipo().equals("Billete")) {
            int numBilletes = (int) (monto / atm.getDenominacion());
            if(numBilletes > atm.getCantidad()) {
                System.out.println("Cantidad "+numBilletes+" Denominacion  "+ atm.getDenominacion());
                numBilletes = atm.getCantidad();
            }
            monto -= numBilletes * atm.getDenominacion();    
            int aux = atm.getCantidad();
            aux -= numBilletes; 
            atm.setCantidad(aux);
            daoJpa.save(atm);
            if (monto < 0) { // Si el monto restante es negativo, la transacción falló
                return false;
            }
            
        } else if (atm.getTipo().equals("Moneda")) {
            int numMonedas = (int) (monto / atm.getDenominacion());
            if(numMonedas > atm.getCantidad()) {
                System.out.println("Cantidad "+numMonedas+" Denominacion  "+ atm.getDenominacion());
                numMonedas = atm.getCantidad();
            }
            monto -= numMonedas * atm.getDenominacion();    
            int aux = atm.getCantidad();
            aux -= numMonedas; 
            atm.setCantidad(aux);
            daoJpa.save(atm);
            if (monto < 0) { // Si el monto restante es negativo, la transacción falló
                return false;
            }
        }
    }

    return true;
}









}
