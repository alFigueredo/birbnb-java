package com.panki.birbnb_backend.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

public class HealthCheckControllerTest extends BaseIntegrationTest {

	@Test
	public void retornarStatus() throws Exception {
		healthCheckMock.perform(get("/api/health"))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith("text/plain"))
				.andExpect(content().string("OK"));
	}

}
