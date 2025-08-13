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
public class Foto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private final String descripcion;
	private final String path;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "alojamiento_id")
	@JsonBackReference
	private final Alojamiento alojamiento;

	protected Foto() {
		this.descripcion = null;
		this.path = null;
		this.alojamiento = null;
	}

	public Foto(String descripcion, String path, Alojamiento alojamiento) {
		this.descripcion = descripcion;
		this.path = path;
		this.alojamiento = alojamiento;
	}

	public Long getId() {
		return id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getPath() {
		return path;
	}

	public Alojamiento getAlojamiento() {
		return alojamiento;
	}

	public Long getAlojamientoId() {
		return getAlojamiento().getId();
	}

}
