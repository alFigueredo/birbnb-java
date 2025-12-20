package com.panki.birbnb_backend.integration;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import net.minidev.json.JSONObject;

public class ReservaControllerTest extends BaseIntegrationTest {

	@Test
	public void retornarReservas() throws Exception {
		reservaMock.perform(get("/api/reservas"))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$.length()").value(3));
	}

	@Test
	public void retornarReserva() throws Exception {
		reservaMock.perform(get("/api/reservas/1"))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$.estadoReserva").exists());
	}

	@Test
	public void reservaNoEncontrada() throws Exception {
		when(reservaRepository.findById(any()))
				.thenReturn(Optional.empty());
		reservaMock.perform(get("/api/reservas/1"))
				.andExpect(status().isNotFound())
				.andExpect(content().string("La reserva 1 no existe"))
				.andExpect(jsonPath("$.estadoReserva").doesNotExist());
	}

	@Test
	public void postReserva() throws Exception {

		final JSONObject reservaJson = new JSONObject();
		reservaJson.put("huespedReservadorId", null);
		reservaJson.put("cantHuespedes", 3);
		reservaJson.put("alojamientoId", null);
		final LocalDate fechaInicio = LocalDate.now().plusYears(10);
		final LocalDate fechaFin = fechaInicio.plusDays(3);
		JSONObject rangoFechasJson = new JSONObject();
		rangoFechasJson.put("fechaInicio", fechaInicio.toString());
		rangoFechasJson.put("fechaFin", fechaFin.toString());
		reservaJson.put("rangoFechas", rangoFechasJson);

		reservaMock.perform(post("/api/reservas")
				.contentType(MediaType.APPLICATION_JSON)
				.content(reservaJson.toString()))
				.andExpect(status().isCreated())
				.andExpect(content().contentType("application/json"));
	}

	@Test
	public void cantHuespedesNoPermitida() throws Exception {

		final JSONObject reservaJson = new JSONObject();
		reservaJson.put("huespedReservadorId", null);
		reservaJson.put("cantHuespedes", 7);
		reservaJson.put("alojamientoId", null);
		final LocalDate fechaInicio = LocalDate.now().plusYears(10);
		final LocalDate fechaFin = fechaInicio.plusDays(3);
		JSONObject rangoFechasJson = new JSONObject();
		rangoFechasJson.put("fechaInicio", fechaInicio.toString());
		rangoFechasJson.put("fechaFin", fechaFin.toString());
		reservaJson.put("rangoFechas", rangoFechasJson);

		reservaMock.perform(post("/api/reservas")
				.contentType(MediaType.APPLICATION_JSON)
				.content(reservaJson.toString()))
				.andExpect(status().isConflict())
				.andExpect(content().string("Cantidad de huéspedes no permitida"));
	}

	@Test
	public void cancelarReserva() throws Exception {
		final JSONObject motivoJson = new JSONObject();
		motivoJson.put("motivo", "Test");
		reservaMock.perform(put("/api/reservas/1/cancelar")
				.contentType(MediaType.APPLICATION_JSON)
				.content(motivoJson.toString()))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"));
	}

	@Test
	public void cancelarReservaPasada() throws Exception {
		when(reservaRepository.findById(any()))
				.thenReturn(Optional.of(reservas[2]));
		final JSONObject motivoJson = new JSONObject();
		motivoJson.put("motivo", "Test");
		reservaMock.perform(put("/api/reservas/1/cancelar")
				.contentType(MediaType.APPLICATION_JSON)
				.content(motivoJson.toString()))
				.andExpect(status().isConflict())
				.andExpect(content().string("Fecha límite de cancelación superada"));
	}

	@Test
	public void confirmarReserva() throws Exception {
		reservaMock.perform(put("/api/reservas/1/confirmar")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"));
	}

	@Test
	public void rechazarReserva() throws Exception {
		final JSONObject motivoJson = new JSONObject();
		motivoJson.put("motivo", "Test");
		reservaMock.perform(put("/api/reservas/1/rechazar")
				.contentType(MediaType.APPLICATION_JSON)
				.content(motivoJson.toString()))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"));
	}

	@Test
	public void putReserva() throws Exception {

		final JSONObject reservaJson = new JSONObject();
		reservaJson.put("cantHuespedes", 2);
		final LocalDate fechaInicio = LocalDate.now().plusYears(3);
		final LocalDate fechaFin = fechaInicio.plusDays(3);
		JSONObject rangoFechasJson = new JSONObject();
		rangoFechasJson.put("fechaInicio", fechaInicio.toString());
		rangoFechasJson.put("fechaFin", fechaFin.toString());
		reservaJson.put("rangoFechas", rangoFechasJson);

		reservaMock.perform(put("/api/reservas/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(reservaJson.toString()))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"));
	}

}
