package com.panki.birbnb_backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Direccion {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private final String calle;
	private final String altura;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ciudad_id")
	@JsonBackReference
	private final Ciudad ciudad;
	private final String latitud;
	private final String longitud;

	protected Direccion() {
		this.calle = null;
		this.altura = null;
		this.ciudad = null;
		this.latitud = null;
		this.longitud = null;
	}

	public Direccion(String calle, String altura, Ciudad ciudad, String latitud, String longitud) {
		this.calle = calle;
		this.altura = altura;
		this.ciudad = ciudad;
		this.latitud = latitud;
		this.longitud = longitud;
	}

	public Long getId() {
		return id;
	}

	public String getCalle() {
		return calle;
	}

	public String getAltura() {
		return altura;
	}

	public Ciudad getCiudad() {
		return ciudad;
	}

	public Long getCiudadId() {
		return getCiudad().getId();
	}

	public String getLatitud() {
		return latitud;
	}

	public String getLongitud() {
		return longitud;
	}

}
