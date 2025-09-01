package com.panki.birbnb_backend.integration;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;

public class NotificacionControllerTest extends BaseIntegrationTest {

	@Test
	public void retornarNotificaciones() throws Exception {
		notificacionMock.perform(get("/api/notificaciones"))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$.length()").value(3));
	}

	@Test
	public void retornarNotificacion() throws Exception {
		notificacionMock.perform(get("/api/notificaciones/1"))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$.mensaje").exists());
	}

	@Test
	public void notificacionNoEncontrada() throws Exception {
		when(notificacionRepository.findById(any()))
				.thenReturn(Optional.empty());
		notificacionMock.perform(get("/api/notificaciones/1"))
				.andExpect(status().isNotFound())
				.andExpect(content().string("La notificación 1 no existe"))
				.andExpect(jsonPath("$.mensaje").doesNotExist());
	}

	@Test
	public void leerNotificacion() throws Exception {
		notificacionMock.perform(put("/api/notificaciones/1/leer"))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$.leida").value(true));
	}

}
