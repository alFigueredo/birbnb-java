package com.panki.birbnb_backend.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

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

	// @Test
	// public void postReserva() throws Exception {
	// reservaMock.perform(post("/api/reservas").)
	// ;
	// }

}
