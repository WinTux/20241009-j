package com.pepe.p20241009.Models;

public class ProveedorGrupo {
	private String ciudad;
	private int cantidad;
	private Long suma;
	
	public ProveedorGrupo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProveedorGrupo(String ciudad, int cantidad, Long suma) {
		super();
		this.ciudad = ciudad;
		this.cantidad = cantidad;
		this.suma = suma;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public Long getSuma() {
		return suma;
	}
	public void setSuma(Long suma) {
		this.suma = suma;
	}
	
}
