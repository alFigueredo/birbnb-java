package com.panki.birbnb_backend.integration;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;

public class UsuarioControllerTest extends BaseIntegrationTest {

	@Test
	public void retornarUsuarios() throws Exception {
		usuarioMock.perform(get("/api/usuarios"))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$.length()").value(2));
	}

	@Test
	public void retornarUsuario() throws Exception {
		usuarioMock.perform(get("/api/usuarios/1"))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$.nombre").exists());
	}

	@Test
	public void usuarioNoEncontrado() throws Exception {
		when(usuarioRepository.findById(any()))
				.thenReturn(Optional.empty());
		usuarioMock.perform(get("/api/usuarios/1"))
				.andExpect(status().isNotFound())
				.andExpect(content().string("El usuario 1 no existe"))
				.andExpect(jsonPath("$.nombre").doesNotExist());
	}

	@Test
	public void retornarNotificaciones() throws Exception {
		usuarioMock.perform(get("/api/usuarios/1/notificaciones"))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$.length()").value(0));
	}

	public void retornarNotificacionesLeidas() throws Exception {
		usuarioMock.perform(get("/api/usuarios/1/notificaciones/leidas"))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$.length()").value(0));
	}

	public void retornarNotificacionesSinLeer() throws Exception {
		usuarioMock.perform(get("/api/usuarios/1/notificaciones/sinleer"))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$.length()").value(0));
	}

}
