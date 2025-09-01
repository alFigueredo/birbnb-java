package com.panki.birbnb_backend.integration;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;

public class AlojamientoControllerTest extends BaseIntegrationTest {

	@Test
	public void retornarAlojamientos() throws Exception {
		alojamientoMock.perform(get("/api/alojamientos"))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$.totalElements").value(2))
				.andExpect(jsonPath("$.size").value(12));
	}

	@Test
	public void retornarAlojamiento() throws Exception {
		alojamientoMock.perform(get("/api/alojamientos/1"))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$.nombre").exists());
	}

	@Test
	public void alojamientoNoEncontrado() throws Exception {
		when(alojamientoRepository.findById(any()))
				.thenReturn(Optional.empty());
		alojamientoMock.perform(get("/api/alojamientos/1"))
				.andExpect(status().isNotFound())
				.andExpect(content().string("El alojamiento 1 no existe"))
				.andExpect(jsonPath("$.nombre").doesNotExist());
	}

}
