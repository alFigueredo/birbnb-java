package com.panki.birbnb_backend.unit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.panki.birbnb_backend.model.enums.Caracteristica;

public class AlojamientoTest extends BaseUnitTest {

	@Test
	public void precioDentroDeRango() {
		assertTrue(alojamientos[0].tuPrecioEstaDentroDe(6000, 8000));
		assertTrue(alojamientos[0].tuPrecioEstaDentroDe(6000, 7000));
		assertTrue(alojamientos[0].tuPrecioEstaDentroDe(7000, 8000));
	}

	@Test
	public void precioFueraDeRango() {
		assertFalse(alojamientos[0].tuPrecioEstaDentroDe(6000, 6999));
		assertFalse(alojamientos[0].tuPrecioEstaDentroDe(7001, 8000));
	}

	@Test
	public void tieneCaracteristica() {
		assertTrue(alojamientos[0].tenesCaracteristica(Caracteristica.WIFI));
		assertTrue(alojamientos[0].tenesCaracteristica(Caracteristica.ESTACIONAMIENTO));
		assertTrue(alojamientos[0].tenesCaracteristica(Caracteristica.PISCINA));
	}

	@Test
	public void noTieneCaracteristica() {
		assertFalse(alojamientos[0].tenesCaracteristica(Caracteristica.MASCOTAS_PERMITIDAS));
	}

	@Test
	public void cantidadPermitidaHuespedes() {
		assertTrue(alojamientos[0].puedenAlojarse(3));
		assertTrue(alojamientos[0].puedenAlojarse(5));
	}

	@Test
	public void cantidadNoPermitidaHuespedes() {
		assertFalse(alojamientos[0].puedenAlojarse(6));
		assertFalse(alojamientos[0].puedenAlojarse(7));
	}

}
