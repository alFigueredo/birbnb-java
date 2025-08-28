package com.panki.birbnb_backend.integration;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.panki.birbnb_backend.controller.ReservaController;
import com.panki.birbnb_backend.exception.GlobalExceptionHandler;
import com.panki.birbnb_backend.model.Alojamiento;
import com.panki.birbnb_backend.model.Ciudad;
import com.panki.birbnb_backend.model.Direccion;
import com.panki.birbnb_backend.model.Pais;
import com.panki.birbnb_backend.model.RangoFechas;
import com.panki.birbnb_backend.model.Reserva;
import com.panki.birbnb_backend.model.Usuario;
import com.panki.birbnb_backend.model.enums.Caracteristica;
import com.panki.birbnb_backend.model.enums.Moneda;
import com.panki.birbnb_backend.model.enums.TipoUsuario;
import com.panki.birbnb_backend.repository.ReservaRepository;
import com.panki.birbnb_backend.service.ReservaService;

abstract class BaseIntegrationTest {

	protected ReservaRepository reservaRepository;
	protected ReservaService reservaService;
	protected MockMvc reservaMock;

	protected Usuario anfitrion;
	protected Set<Caracteristica> caracteristicas;
	protected Pais pais;
	protected Ciudad ciudad;
	protected Direccion direccion;
	protected Alojamiento[] alojamientos;
	protected Usuario huesped;
	protected RangoFechas[] rangoFechas;
	protected Reserva[] reservas;

	@BeforeEach
	public void init() {

		reservaRepository = Mockito.mock(ReservaRepository.class);
		reservaService = new ReservaService(reservaRepository, null, null);
		reservaMock = MockMvcBuilders.standaloneSetup(new ReservaController(reservaService))
				.setControllerAdvice(new GlobalExceptionHandler()).build();

		anfitrion = new Usuario(
				"John Doe",
				"johndoe@gmail.com",
				TipoUsuario.ANFITRION);
		caracteristicas = Set.of(
				Caracteristica.WIFI,
				Caracteristica.ESTACIONAMIENTO,
				Caracteristica.PISCINA);
		pais = new Pais("Argentina");
		ciudad = new Ciudad("Buenos Aires", pais);
		direccion = new Direccion("Avenida Corrientes", "1234", ciudad, "1", "1");
		alojamientos = new Alojamiento[] {
				new Alojamiento(
						anfitrion,
						"El Bolson",
						"Un lindo lugar",
						7000,
						Moneda.PESO_ARG,
						"14:00",
						"10:00",
						direccion,
						5,
						caracteristicas),
				new Alojamiento(
						null,
						"El Bolson",
						"Un lindo lugar",
						7000,
						Moneda.PESO_ARG,
						"14:00",
						"10:00",
						direccion,
						5,
						caracteristicas)
		};
		huesped = new Usuario("John Doe", "johndoe@gmail.con", TipoUsuario.HUESPED);
		rangoFechas = new RangoFechas[] {
				new RangoFechas(LocalDate.of(2025, 5, 3), LocalDate.of(2025, 5, 5)),
				new RangoFechas(LocalDate.of(2025, 5, 6), LocalDate.of(2025, 5, 9)),
				new RangoFechas(LocalDate.of(2025, 5, 4), LocalDate.of(2025, 5, 6)),
				new RangoFechas(LocalDate.of(2025, 5, 3), LocalDate.of(2025, 5, 6)),
				new RangoFechas(LocalDate.of(2025, 5, 2), LocalDate.of(2025, 5, 5)) };
		reservas = new Reserva[] {
				new Reserva(huesped, 4, alojamientos[0], rangoFechas[0]),
				new Reserva(huesped, 4, alojamientos[1], rangoFechas[0])
		};

		when(reservaRepository.findAll())
				.thenReturn(List.of(
						reservas[0],
						reservas[1]));
	}
}
