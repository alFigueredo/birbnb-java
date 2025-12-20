package com.panki.birbnb_backend.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

public class HuespedControllerTest extends BaseIntegrationTest {

	@Test
	public void retornarReservasHuesped() throws Exception {
		huespedMock.perform(get("/api/huespedes/1/reservas"))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$.length()").value(0));
	}
}
