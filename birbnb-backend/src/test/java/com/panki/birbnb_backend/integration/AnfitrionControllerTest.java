package com.panki.birbnb_backend.integration;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;

public class AnfitrionControllerTest extends BaseIntegrationTest {

	@Test
	public void retornarReservasAnfitrion() throws Exception {
		when(usuarioRepository.findById(any()))
				.thenReturn(Optional.of(anfitrion));
		anfitrionMock.perform(get("/api/anfitriones/1/reservas"))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$.length()").value(3));
	}
}
