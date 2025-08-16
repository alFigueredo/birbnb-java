package com.panki.birbnb_backend.model;

import java.time.LocalDate;

import com.panki.birbnb_backend.exception.ValidationException;

import jakarta.persistence.Embeddable;

@Embeddable
public class RangoFechas {

	private LocalDate fechaInicio;
	private LocalDate fechaFin;

	protected RangoFechas() {
		this.fechaInicio = null;
		this.fechaFin = null;
	}

	public RangoFechas(LocalDate fechaInicio, LocalDate fechaFin) {
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		if (getFechaFin().isBefore(getFechaInicio()))
			throw new ValidationException("Rango de fechas incorrecto");
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	public int cantidadDias() {
		return getFechaFin().compareTo(getFechaInicio());
	}

	public void modificarRangoFechas(RangoFechas rangoFechas) {
		setFechaInicio(rangoFechas.getFechaInicio());
		setFechaFin(rangoFechas.getFechaInicio());
	}

	public boolean haySolapamiento(RangoFechas rangoFechas) {
		return rangoFechas.getFechaInicio().isBefore(getFechaFin())
				&& rangoFechas.getFechaFin().isAfter(getFechaInicio());
	}

}
