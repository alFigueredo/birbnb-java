package com.panki.birbnb_backend.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Ciudad {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private final String nombre;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pais_id")
	@JsonBackReference
	private final Pais pais;
	@OneToMany(mappedBy = "ciudad", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonManagedReference
	private final List<Direccion> direcciones = new ArrayList<>();

	protected Ciudad() {
		this.nombre = null;
		this.pais = null;
	}

	public Ciudad(String nombre, Pais pais) {
		this.nombre = nombre;
		this.pais = pais;
	}

	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public Pais getPais() {
		return pais;
	}

	public Long getPaisID() {
		return getPais().getId();
	}

	public List<Direccion> getDirecciones() {
		return direcciones;
	}

	public void agregarDireccion(String calle, String altura, String latitud, String longitud) {
		final Direccion direccion = new Direccion(calle, altura, this, latitud, longitud);
		direcciones.add(direccion);
	}

}
