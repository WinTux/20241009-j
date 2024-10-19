package com.pepe.p20241009.Models;

public class ComponenteJoin {
	private int e;
	private String color;
	private int cantidad;
	public ComponenteJoin() {}
	
	public ComponenteJoin(int e, String color, int cantidad) {
		super();
		this.e = e;
		this.color = color;
		this.cantidad = cantidad;
	}

	public int getE() {
		return e;
	}
	public void setE(int e) {
		this.e = e;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
}
