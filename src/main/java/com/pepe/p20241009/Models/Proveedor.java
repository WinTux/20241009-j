package com.pepe.p20241009.Models;

import java.util.Set;

public class Proveedor {
	private String p;
	private String pnombre;
	private int categoria;
	private String ciudad;
	private Set envios;
	public Proveedor() {}
	public String getP() {
		return p;
	}
	public void setP(String p) {
		this.p = p;
	}
	public String getPnombre() {
		return pnombre;
	}
	public void setPnombre(String pnombre) {
		this.pnombre = pnombre;
	}
	public int getCategoria() {
		return categoria;
	}
	public void setCategoria(int categoria) {
		this.categoria = categoria;
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
