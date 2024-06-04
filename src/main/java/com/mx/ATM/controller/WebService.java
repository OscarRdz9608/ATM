package com.mx.ATM.controller;

import org.springframework.web.bind.annotation.RestController;

import com.mx.ATM.domain.Atm;
import com.mx.ATM.domain.montoDTO;
import com.mx.ATM.service.ImpAtm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/atm")
@CrossOrigin("*")
public class WebService {

	@Autowired
	ImpAtm imp;

	@GetMapping("")
	public ResponseEntity<String> test() {
		System.out.println("Endpoint de prueba alcanzado");
		return ResponseEntity.ok("OK");
	}

	@GetMapping("available")
	public ResponseEntity<List<Atm>> getAvailable() {
		Map<String, String> respuesta = new HashMap<>();
		HttpStatus status;
		if (!imp.listAvailable().isEmpty()) {
			return ResponseEntity.ok(imp.listAvailable());
		} else {
			respuesta.put("response", "NO_CONTENT");
			status = HttpStatus.NO_CONTENT;
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("saldo")
	public ResponseEntity<Double> getSaldo() {
		Map<String, String> respuesta = new HashMap<>();
		if (imp.getSaldo() > 0.50) {
			return ResponseEntity.ok(imp.getSaldo());
		} else {
			respuesta.put("response", "NO_CONTENT");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	
	
	
	
	@PostMapping("retiro")
	  public ResponseEntity<Map<String, String>> retirar(@RequestBody montoDTO monto) {
		Map<String, String> respuesta = new HashMap<>();
		HttpStatus status;
		if (imp.distribuirDinero1(monto.getMonto())) {
			respuesta.put("Mensaje", "Se completo el retiro");
		    status = HttpStatus.OK;
	       
		} else {
			respuesta.put("response", "NO_CONTENT");
			  status = HttpStatus.CONFLICT;		
			 }
        return new ResponseEntity<>(respuesta, status);   	

	}
	
	
	@GetMapping("reload")
	public void reload() {
		imp.reload();
	}

}
