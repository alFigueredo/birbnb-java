package com.panki.birbnb_backend.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

}
