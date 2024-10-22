package com.pepe.p20241009.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@Table(name="envio", schema="produccion")
public class Envio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int e;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="p", nullable=false)
	private Proveedor p;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="c")
	
	//@OneToOne(fetch=FetchType.LAZY)
	//@PrimaryKeyJoinColumn
	
	/*
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="detalles_envio", 
	catalog="abcd", 
	joinColumns= {
			@JoinColumn(name="id_d_envio", nullable=false,updatable=false)
	}, inverseJoinColumns= {
			@JoinColumn(name="detalle_env", nullable=false,updatable=false)
	})
	*/
	
	private Componente c;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="t")
	private Articulo t;
	@Column(nullable=false)
	private int cantidad;
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public Proveedor getP() {
		return p;
	}
	public void setP(Proveedor p) {
		this.p = p;
	}
	public Componente getC() {
		return c;
	}
	public void setC(Componente c) {
		this.c = c;
	}
	public Articulo getT() {
		return t;
	}
	public void setT(Articulo t) {
		this.t = t;
	}
	
}
