package com.panki.birbnb_backend.dto;

public class FiltrosAlojamientoDTO {

	private final String nombre;
	private final Integer precioGt;
	private final Integer precioLt;
	private final Integer cantHuespedes;

	public FiltrosAlojamientoDTO() {
		this.nombre = null;
		this.precioGt = null;
		this.precioLt = null;
		this.cantHuespedes = null;
	}

	public FiltrosAlojamientoDTO(String nombre, Integer precioGt, Integer precioLt, Integer cantHuespedes) {
		this.nombre = nombre;
		this.precioGt = precioGt;
		this.precioLt = precioLt;
		this.cantHuespedes = cantHuespedes;
	}

	public String getNombre() {
		return nombre;
	}

	public Integer getPrecioGt() {
		return precioGt;
	}

	public Integer getPrecioLt() {
		return precioLt;
	}

	public Integer getCantHuespedes() {
		return cantHuespedes;
	}

}
