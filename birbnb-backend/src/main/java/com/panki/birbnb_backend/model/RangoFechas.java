package com.panki.birbnb_backend.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class RangoFechas {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private final LocalDate fechaInicio;
	private final LocalDate fechaFin;

	protected RangoFechas() {
		this.fechaInicio = null;
		this.fechaFin = null;
	}

	public RangoFechas(LocalDate fechaInicio, LocalDate fechaFin) {
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}

	public Long getId() {
		return id;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

}
