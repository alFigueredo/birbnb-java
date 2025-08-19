package com.panki.birbnb_backend.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import com.panki.birbnb_backend.controller.ReservaController;

@WebMvcTest(ReservaController.class)
public class ReservaControllerTest extends BaseIntegrationTest {

	@Test
	public void retornarReservas() throws Exception {
		mockMvc.perform(get("/api/reservas"))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"));
	}

}
