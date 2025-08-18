package com.panki.birbnb_backend.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.panki.birbnb_backend.controller.ReservaController;
import com.panki.birbnb_backend.service.ReservaService;

@WebMvcTest(ReservaController.class)
public class ReservaControllerTest extends BaseIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@MockitoBean
	private ReservaService reservaService;

	@Test
	public void retornarReservas() throws Exception {
		mockMvc.perform(get("/api/reservas"))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"));
	}

}
