package com.panki.birbnb_backend.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import com.panki.birbnb_backend.model.enums.EstadoReserva;

public class ReservaTest extends BaseUnitTest {

	@Test
	public void reservaEnEstadoPendiente() {
		assertEquals(EstadoReserva.PENDIENTE, reservas[0].getEstadoReserva());
		assertFalse(reservas[0].reservaRespondidaPorAnfitrion());
		assertFalse(reservas[0].reservaAnulada());
	}
}
