package com.panki.birbnb_backend.dto;

import com.panki.birbnb_backend.model.RangoFechas;

public class ReservaDTO {

	private final Long huespedReservadorId;
	private final int cantHuespedes;
	private final Long alojamientoId;
	private final RangoFechas rangoFechas;

	public ReservaDTO() {
		huespedReservadorId = null;
		cantHuespedes = 0;
		alojamientoId = null;
		rangoFechas = null;
	}

	public ReservaDTO(Long huespedReservadorId, int cantHuespedes, Long alojamientoId, RangoFechas rangoFechas) {
		this.huespedReservadorId = huespedReservadorId;
		this.cantHuespedes = cantHuespedes;
		this.alojamientoId = alojamientoId;
		this.rangoFechas = rangoFechas;
	}

	public Long getHuespedReservadorId() {
		return huespedReservadorId;
	}

	public int getCantHuespedes() {
		return cantHuespedes;
	}

	public Long getAlojamientoId() {
		return alojamientoId;
	}

	public RangoFechas getRangoFechas() {
		return rangoFechas;
	}

}
