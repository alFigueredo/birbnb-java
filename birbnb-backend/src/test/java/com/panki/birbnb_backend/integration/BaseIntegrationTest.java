package com.panki.birbnb_backend.integration;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.panki.birbnb_backend.controller.AlojamientoController;
import com.panki.birbnb_backend.controller.HealthCheckController;
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
import com.panki.birbnb_backend.repository.AlojamientoRepository;
import com.panki.birbnb_backend.repository.ReservaRepository;
import com.panki.birbnb_backend.repository.UsuarioRepository;
import com.panki.birbnb_backend.service.AlojamientoService;
import com.panki.birbnb_backend.service.ReservaService;
import com.panki.birbnb_backend.service.UsuarioService;

abstract class BaseIntegrationTest {

	protected ReservaRepository reservaRepository;
	protected UsuarioRepository usuarioRepository;
	protected AlojamientoRepository alojamientoRepository;
	protected ReservaService reservaService;
	protected UsuarioService usuarioService;
	protected AlojamientoService alojamientoService;
	protected MockMvc alojamientoMock;
	protected MockMvc reservaMock;
	protected MockMvc healthCheckMock;

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

		usuarioRepository = Mockito.mock(UsuarioRepository.class);
		alojamientoRepository = Mockito.mock(AlojamientoRepository.class);
		reservaRepository = Mockito.mock(ReservaRepository.class);
		usuarioService = new UsuarioService(usuarioRepository);
		alojamientoService = new AlojamientoService(alojamientoRepository);
		reservaService = new ReservaService(reservaRepository, alojamientoService, usuarioService);
		alojamientoMock = MockMvcBuilders.standaloneSetup(new AlojamientoController(alojamientoService))
				.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
				.setControllerAdvice(new GlobalExceptionHandler()).build();
		reservaMock = MockMvcBuilders.standaloneSetup(new ReservaController(reservaService))
				.setControllerAdvice(new GlobalExceptionHandler()).build();
		healthCheckMock = MockMvcBuilders.standaloneSetup(new HealthCheckController())
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
						anfitrion,
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
		final int currentYearPlusOne = LocalDate.now().plusYears(1).getYear();
		rangoFechas = new RangoFechas[] {
				new RangoFechas(LocalDate.of(currentYearPlusOne, 5, 3), LocalDate.of(currentYearPlusOne, 5, 5)),
				new RangoFechas(LocalDate.of(currentYearPlusOne, 5, 6), LocalDate.of(currentYearPlusOne, 5, 9)),
				new RangoFechas(LocalDate.of(currentYearPlusOne, 5, 4), LocalDate.of(currentYearPlusOne, 5, 6)),
				new RangoFechas(LocalDate.of(2009, 5, 10), LocalDate.of(2009, 5, 12)),
				new RangoFechas(LocalDate.of(currentYearPlusOne, 5, 3), LocalDate.of(currentYearPlusOne, 5, 5)) };
		reservas = new Reserva[] {
				new Reserva(huesped, 3, alojamientos[0], rangoFechas[0]),
				new Reserva(huesped, 4, alojamientos[0], rangoFechas[1]),
				new Reserva(huesped, 3, alojamientos[0], rangoFechas[3])
		};

		final Pageable pageable = Pageable.ofSize(12);
		final Page<Alojamiento> alojamientosPage = new PageImpl<>(Arrays.stream(alojamientos).toList(), pageable,
				alojamientos.length);
		when(alojamientoRepository.findAll(ArgumentMatchers.<Specification<Alojamiento>>any(), eq(pageable)))
				.thenReturn(alojamientosPage);
		when(alojamientoRepository.findById(any()))
				.thenReturn(Optional.of(alojamientos[0]));

		when(reservaRepository.findAll())
				.thenReturn(Arrays.stream(reservas).toList());
		when(reservaRepository.findById(any()))
				.thenReturn(Optional.of(reservas[0]));
		when(usuarioRepository.findById(any()))
				.thenReturn(Optional.of(huesped));
		when(reservaRepository.save(any()))
				.thenReturn(reservas[0]);
	}
}
