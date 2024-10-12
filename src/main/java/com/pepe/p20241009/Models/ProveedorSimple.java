package com.pepe.p20241009.Models;

import java.io.Serializable;

public class ProveedorSimple implements Serializable {
	private static final long serialVersionUID = 1305760182771079567L;
	private String p;
	private String pnombre;
	private String ciudad;
	public ProveedorSimple() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ProveedorSimple(String p, String pnombre, String ciudad) {
		super();
		this.p = p;
		this.pnombre = pnombre;
		this.ciudad = ciudad;
	}

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
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
}
