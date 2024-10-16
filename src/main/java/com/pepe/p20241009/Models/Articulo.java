package com.pepe.p20241009.Models;


import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="articulo", schema="produccion")
public class Articulo {
	@Id
	private String t;
	@Column(nullable=false)
	private String tnombre;
	@Column(nullable=false)
	private String ciudad;
	@OneToMany(fetch=FetchType.LAZY, mappedBy="t", targetEntity=Envio.class)
	private Set envios;
	public Articulo() {
		super();
	}
	public String getT() {
		return t;
	}
	public void setT(String t) {
		this.t = t;
	}
	public String getTnombre() {
		return tnombre;
	}
	public void setTnombre(String tnombre) {
		this.tnombre = tnombre;
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
