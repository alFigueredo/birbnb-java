package com.panki.birbnb_backend.dto;

import java.util.Set;

import com.panki.birbnb_backend.model.enums.Caracteristica;

public class FiltrosAlojamientoDTO {

	private final String nombre;
	private final Integer precioGt;
	private final Integer precioLt;
	private final Integer cantHuespedes;
	private final Set<Caracteristica> caracteristicas;
	private final String ciudad;
	private final String pais;

	public FiltrosAlojamientoDTO() {
		this.nombre = null;
		this.precioGt = null;
		this.precioLt = null;
		this.cantHuespedes = null;
		this.caracteristicas = null;
		this.ciudad = null;
		this.pais = null;
	}

	public FiltrosAlojamientoDTO(String nombre, Integer precioGt, Integer precioLt, Integer cantHuespedes,
			Set<Caracteristica> caracteristicas, String ciudad, String pais) {
		this.nombre = nombre;
		this.precioGt = precioGt;
		this.precioLt = precioLt;
		this.cantHuespedes = cantHuespedes;
		this.caracteristicas = caracteristicas;
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

	public Set<Caracteristica> getCaracteristicas() {
		return caracteristicas;
	}

	public String getCiudad() {
		return ciudad;
	}

	public String getPais() {
		return pais;
	}

}
