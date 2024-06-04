package com.mx.ATM.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="ATM")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Data
public class Atm {

	@Id
	@Column(name="ID", columnDefinition = "NUMBER", length = 10, nullable = false)
	private int id;
	
	@Column(name="TIPO", columnDefinition = "NVARCHAR2(10)", length = 10, nullable = false)
	private String tipo;
	
	@Column(name="CANTIDAD", columnDefinition = "NUMBER", length = 5, nullable = false)
	private int cantidad;
	
	@Column(name="DENOMINACION", columnDefinition = "number", length = 5, nullable = false)
	private double denominacion;
	

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(double denominacion) {
		this.denominacion = denominacion;
	}
	
	
	
	
}
