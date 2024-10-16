package com.pepe.p20241009.Models;


import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Entity
@Table(name="componente", schema="produccion")
public class Componente {
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String c; // DDBB se hace cargo, el usuario se hace cargo, la app se hace cargo
	@Column(name="cnombre", unique=true, nullable=false)
	private String nombre;
	private String color;
	private int peso;
	private String ciudad;
	@OneToMany(fetch=FetchType.LAZY, mappedBy="c", targetEntity=Envio.class)
	private Set envios;
	public Componente() {
		
	}
	public String getC() {
		return c;
	}
	public void setC(String c) {
		this.c = c;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public Set getEnvios() {
		return envios;
	}
	public void setEnvios(Set envios) {
		this.envios = envios;
	}
	
}
