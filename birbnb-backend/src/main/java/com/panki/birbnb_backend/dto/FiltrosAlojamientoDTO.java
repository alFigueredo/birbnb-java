package com.panki.birbnb_backend.dto;

public class FiltrosAlojamientoDTO {

	private final String nombre;
	private final Integer precioGt;
	private final Integer precioLt;
	private final Integer cantHuespedes;
	private final String ciudad;
	private final String pais;

	public FiltrosAlojamientoDTO() {
		this.nombre = null;
		this.precioGt = null;
		this.precioLt = null;
		this.cantHuespedes = null;
		this.ciudad = null;
		this.pais = null;
	}

	public FiltrosAlojamientoDTO(String nombre, Integer precioGt, Integer precioLt, Integer cantHuespedes,
			String ciudad, String pais) {
		this.nombre = nombre;
		this.precioGt = precioGt;
		this.precioLt = precioLt;
		this.cantHuespedes = cantHuespedes;
		this.ciudad = ciudad;
		this.pais = pais;
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

	public String getCiudad() {
		return ciudad;
	}

	public String getPais() {
		return pais;
	}

}
