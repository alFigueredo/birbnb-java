package com.panki.birbnb_backend.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Pais {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private final String nombre;
	@OneToMany(mappedBy = "pais", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonManagedReference
	private final List<Ciudad> ciudades = new ArrayList<>();

	protected Pais() {
		this.nombre = null;
	}

	public Pais(String nombre) {
		this.nombre = nombre;
	}

	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public List<Ciudad> getCiudades() {
		return ciudades;
	}

	public void agregarCiudad(String nombre) {
		final Ciudad ciudad = new Ciudad(nombre, this);
		ciudades.add(ciudad);
	}
}
