package com.panki.birbnb_backend.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.panki.birbnb_backend.model.enums.EstadoReserva;

public class ReservaTest extends BaseUnitTest {

	@Test
	public void reservaEnEstadoPendiente() {
		assertEquals(EstadoReserva.PENDIENTE, reservas[0].getEstadoReserva());
		assertFalse(reservas[0].reservaRespondidaPorAnfitrion());
		assertFalse(reservas[0].reservaAnulada());
	}

	@Test
	public void lasReservasNoSeSolapan() {
		assertFalse(reservas[0].haySolapamiento(rangoFechas[1]));
	}

	@Test
	public void lasReservasSeSolapan() {
		assertTrue(reservas[0].haySolapamiento(rangoFechas[2]));
	}

	@Test
	public void laReservaEstaAnulada() {
		reservas[0].actualizarEstado(EstadoReserva.CANCELADA);
		assertFalse(reservas[0].haySolapamiento(rangoFechas[1]));
		reservas[0].actualizarEstado(EstadoReserva.RECHAZADA);
		assertFalse(reservas[0].haySolapamiento(rangoFechas[1]));
	}
}
