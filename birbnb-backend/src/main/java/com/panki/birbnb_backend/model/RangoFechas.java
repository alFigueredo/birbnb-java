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
	}

	public void esValido() {
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

	public boolean antesDeFechaInicio() {
		return getFechaInicio().isAfter(LocalDate.now());
	}

	public void modificarRangoFechas(RangoFechas rangoFechas) {
		setFechaInicio(rangoFechas.getFechaInicio());
		setFechaFin(rangoFechas.getFechaFin());
	}

	public boolean haySolapamiento(RangoFechas rangoFechas) {
		return rangoFechas.getFechaInicio().isBefore(getFechaFin())
				&& rangoFechas.getFechaFin().isAfter(getFechaInicio());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fechaInicio == null) ? 0 : fechaInicio.hashCode());
		result = prime * result + ((fechaFin == null) ? 0 : fechaFin.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RangoFechas other = (RangoFechas) obj;
		if (fechaInicio == null) {
			if (other.fechaInicio != null)
				return false;
		} else if (!fechaInicio.equals(other.fechaInicio))
			return false;
		if (fechaFin == null) {
			if (other.fechaFin != null)
				return false;
		} else if (!fechaFin.equals(other.fechaFin))
			return false;
		return true;
	}

}
