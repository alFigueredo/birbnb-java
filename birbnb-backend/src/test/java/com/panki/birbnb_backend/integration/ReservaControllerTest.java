package com.panki.birbnb_backend.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.panki.birbnb_backend.dto.ReservaDTO;

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

}
