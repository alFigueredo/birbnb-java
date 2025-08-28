package com.panki.birbnb_backend.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.panki.birbnb_backend.exception.ValidationException;
import com.panki.birbnb_backend.model.RangoFechas;

public class RangoFechasTest extends BaseUnitTest {

	@Test
	public void fechaInicialMayorQueFechaFinal() {
		ValidationException exc = assertThrows(ValidationException.class,
				() -> new RangoFechas(LocalDate.of(2025, 5, 3), LocalDate.of(2025, 5, 2)));
		assertEquals("Rango de fechas incorrecto", exc.getMessage());
	}

	@Test
	public void lasFechasSeSolapan() {
		assertTrue(rangoFechas[0].haySolapamiento(rangoFechas[2]));
	}

	@Test
	public void lasFechasNoSeSolapan() {
		assertFalse(rangoFechas[0].haySolapamiento(rangoFechas[1]));
	}

	@Test
	public void cantidadDias() {
		assertEquals(2, rangoFechas[0].cantidadDias());
		assertEquals(3, rangoFechas[1].cantidadDias());
	}

	@Test
	public void rangoFechasNoSonIguales() {
		assertFalse(rangoFechas[0].equals(rangoFechas[1]));
	}

	@Test
	public void rangoFechasSonIguales() {
		assertTrue(rangoFechas[0].equals(rangoFechas[4]));
	}

}
