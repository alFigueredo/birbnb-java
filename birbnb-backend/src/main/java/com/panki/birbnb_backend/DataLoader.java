package com.panki.birbnb_backend;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
import com.panki.birbnb_backend.repository.PaisRepository;
import com.panki.birbnb_backend.repository.ReservaRepository;
import com.panki.birbnb_backend.repository.UsuarioRepository;

@Component
public class DataLoader implements CommandLineRunner {

	private final UsuarioRepository usuarioRepository;
	private final AlojamientoRepository alojamientoRepository;
	private final ReservaRepository reservaRepository;
	private final PaisRepository paisRepository;

	public DataLoader(UsuarioRepository usuarioRepository, AlojamientoRepository alojamientoRepository,
			ReservaRepository reservaRepository, PaisRepository paisRepository) {
		this.usuarioRepository = usuarioRepository;
		this.alojamientoRepository = alojamientoRepository;
		this.reservaRepository = reservaRepository;
		this.paisRepository = paisRepository;
	}

	@Override
	public void run(String... args) throws Exception {

		System.out.println("=== Precargando datos de prueba ===");

		alojamientoRepository.deleteAll();
		usuarioRepository.deleteAll();
		paisRepository.deleteAll();

		final Usuario[] huespedes = {
				new Usuario("John Doe", "johndoe@gmail.com", TipoUsuario.HUESPED),
				new Usuario("Marta Aguero", "maguero@gmail.com", TipoUsuario.HUESPED),
				new Usuario("Carlos Perez", "cperez@gmail.com", TipoUsuario.HUESPED)
		};

		huespedes[0].agregarNotificacion("¡Gracias por reservar con nosotros!");
		huespedes[1]
				.agregarNotificacion("¡Oferta especial! 20% de descuento en todos los productos hasta la medianoche.");
		huespedes[2].agregarNotificacion("Mensaje 3");
		huespedes[1].agregarNotificacion(
				"¡Esperamos que hayas disfrutado de tu estancia! ¿Podrías dejarnos una evaluación sobre tu experiencia en  Tu opinión es importante para nosotros");
		huespedes[2].agregarNotificacion("Mensaje 5");

		for (final Usuario huesped : huespedes)
			usuarioRepository.save(huesped);

		final Usuario[] anfitriones = { new Usuario("Jane Doe", "janedoe@gmail.com", TipoUsuario.ANFITRION) };

		for (final Usuario anfitrion : anfitriones)
			usuarioRepository.save(anfitrion);

		final Pais[] paises = {
				new Pais("Argentina"),
				new Pais("Brasil"),
		};

		paises[0].agregarCiudad("Buenos Aires");
		paises[1].agregarCiudad("Rio de Janeiro");

		final Ciudad[] ciudades = Stream
				.concat(paises[0].getCiudades().stream(), paises[1].getCiudades().stream())
				.toArray(Ciudad[]::new);

		ciudades[0].agregarDireccion("Avenida Corrientes", "1234", "1", "1");
		ciudades[0].agregarDireccion("Calle Defensa", "876", "2", "2");
		ciudades[0].agregarDireccion("Avenida Santa Fe", "3200", "1", "2");
		ciudades[0].agregarDireccion("Calle Lavalle", "234", "1", "2");
		ciudades[0].agregarDireccion("Avenida Rivadavia", "5600", "1", "3");
		ciudades[0].agregarDireccion("Calle Florida", "100", "3", "3");
		ciudades[0].agregarDireccion("Avenida Belgrano", "900", "3", "1");
		ciudades[0].agregarDireccion("Calle Suipacha", "350", "4", "6");
		ciudades[0].agregarDireccion("Avenida Callao", "1500", "6", "1");
		ciudades[1].agregarDireccion("Avenida Atlântica", "1702", "1", "1");
		ciudades[1].agregarDireccion("Calle Falsa", "1", "1", "1");
		ciudades[1].agregarDireccion("Calle Falsa", "2", "1", "1");
		ciudades[1].agregarDireccion("Calle Falsa", "3", "1", "1");
		ciudades[1].agregarDireccion("Calle Falsa", "4", "1", "1");

		final Direccion[] direcciones = Stream
				.concat(ciudades[0].getDirecciones().stream(), ciudades[1].getDirecciones().stream())
				.toArray(Direccion[]::new);

		for (final Pais pais : paises)
			paisRepository.save(pais);

		final Alojamiento[] alojamientos = {
				new Alojamiento(
						anfitriones[0],
						"Hotel Corrientes Palace",
						"Alojamiento 1",
						7000,
						Moneda.PESO_ARG,
						"14:00",
						"10:00",
						direcciones[0],
						5,
						Set.of(Caracteristica.WIFI, Caracteristica.ESTACIONAMIENTO)),
				new Alojamiento(
						anfitriones[0],
						"Posada Defensa Colonial",
						"Alojamiento 2",
						6000,
						Moneda.PESO_ARG,
						"14:00",
						"10:00",
						direcciones[1],
						6,
						Set.of(Caracteristica.WIFI, Caracteristica.ESTACIONAMIENTO)),
				new Alojamiento(
						anfitriones[0],
						"Santa Fe Executive Suites",
						"Alojamiento 3",
						8000,
						Moneda.PESO_ARG,
						"14:00",
						"10:00",
						direcciones[2],
						7,
						Set.of(Caracteristica.WIFI, Caracteristica.ESTACIONAMIENTO)),
				new Alojamiento(
						anfitriones[0],
						"Residencia Lavalle",
						"Alojamiento 4",
						7000,
						Moneda.PESO_ARG,
						"14:00",
						"10:00",
						direcciones[3],
						5,
						Set.of(Caracteristica.WIFI, Caracteristica.MASCOTAS_PERMITIDAS)),
				new Alojamiento(
						anfitriones[0],
						"Miravida SOHO",
						"Alojamiento 5",
						7000,
						Moneda.PESO_ARG,
						"14:00",
						"10:00",
						direcciones[4],
						5,
						Set.of(Caracteristica.WIFI, Caracteristica.ESTACIONAMIENTO)),
				new Alojamiento(
						anfitriones[0],
						"Infinity Copacabana hotel",
						"Alojamiento 6",
						8000,
						Moneda.PESO_ARG,
						"14:00",
						"10:00",
						direcciones[9],
						5,
						Set.of(Caracteristica.WIFI, Caracteristica.ESTACIONAMIENTO)),
				new Alojamiento(
						anfitriones[0],
						"Argenta Suites",
						"Alojamiento 7",
						10000,
						Moneda.PESO_ARG,
						"14:00",
						"10:00",
						direcciones[5],
						5,
						Set.of(Caracteristica.WIFI,
								Caracteristica.ESTACIONAMIENTO,
								Caracteristica.PISCINA)),
				new Alojamiento(
						anfitriones[0],
						"Ayres de Recoleta Plaza",
						"Alojamiento 8",
						7500,
						Moneda.PESO_ARG,
						"14:00",
						"10:00",
						direcciones[6],
						5,
						Set.of(Caracteristica.WIFI, Caracteristica.ESTACIONAMIENTO)),
				new Alojamiento(
						anfitriones[0],
						"San Telmo Suites",
						"Alojamiento 9",
						11000,
						Moneda.PESO_ARG,
						"14:00",
						"10:00",
						direcciones[7],
						5,
						Set.of(Caracteristica.WIFI,
								Caracteristica.ESTACIONAMIENTO,
								Caracteristica.PISCINA)),
				new Alojamiento(
						anfitriones[0],
						"Ayres Duplex Suites",
						"Alojamiento 10",
						7000,
						Moneda.PESO_ARG,
						"14:00",
						"10:00",
						direcciones[8],
						5,
						Set.of(Caracteristica.WIFI, Caracteristica.ESTACIONAMIENTO)),
				new Alojamiento(
						anfitriones[0],
						"Mine Hotel Boutique",
						"Alojamiento 11",
						9000,
						Moneda.PESO_ARG,
						"14:00",
						"10:00",
						direcciones[10],
						5,
						Set.of(Caracteristica.WIFI,
								Caracteristica.ESTACIONAMIENTO,
								Caracteristica.PISCINA)),
				new Alojamiento(
						anfitriones[0],
						"Casa San Jose",
						"Alojamiento 12",
						7000,
						Moneda.PESO_ARG,
						"14:00",
						"10:00",
						direcciones[11],
						5,
						Set.of(Caracteristica.WIFI, Caracteristica.ESTACIONAMIENTO)),
				new Alojamiento(
						anfitriones[0],
						"Hilton Puerto Madero",
						"Alojamiento 13",
						7000,
						Moneda.PESO_ARG,
						"14:00",
						"10:00",
						direcciones[12],
						5,
						Set.of(Caracteristica.WIFI, Caracteristica.ESTACIONAMIENTO)),
				new Alojamiento(
						anfitriones[0],
						"Hotel Rural Casa Grande",
						"Alojamiento 14",
						7000,
						Moneda.PESO_ARG,
						"14:00",
						"10:00",
						direcciones[13],
						5,
						Set.of(Caracteristica.WIFI, Caracteristica.ESTACIONAMIENTO))
		};

		alojamientos[0].agregarFotos("Foto casa 1", "/fotos/casa1.jpg");
		alojamientos[1].agregarFotos("Foto casa 2", "/fotos/casa2.jpg");
		alojamientos[2].agregarFotos("Foto casa 3", "/fotos/casa3.jpg");
		alojamientos[3].agregarFotos("Foto casa 4", "/fotos/casa4.jpg");
		alojamientos[4].agregarFotos("Foto casa 5", "/fotos/casa5.jpg");
		alojamientos[5].agregarFotos("Foto casa 6", "/fotos/casa6.jpg");
		alojamientos[6].agregarFotos("Foto casa 7", "/fotos/casa7.jpg");
		alojamientos[7].agregarFotos("Foto casa 8", "/fotos/casa8.jpg");
		alojamientos[8].agregarFotos("Foto casa 9", "/fotos/casa9.jpg");
		alojamientos[9].agregarFotos("Foto casa 10", "/fotos/casa10.jpg");
		alojamientos[10].agregarFotos("Foto casa 11", "/fotos/casa11.jpg");
		alojamientos[11].agregarFotos("Foto casa 12", "/fotos/casa12.jpg");
		alojamientos[12].agregarFotos("Foto casa 13", "/fotos/casa13.jpg");
		alojamientos[13].agregarFotos("Foto casa 14", "/fotos/casa14.jpg");
		alojamientos[1].agregarFotos("Foto casa 15", "/fotos/casa15.jpg");
		alojamientos[1].agregarFotos("Foto casa 16", "/fotos/casa16.jpg");

		for (final Alojamiento alojamiento : alojamientos)
			alojamientoRepository.save(alojamiento);

		final RangoFechas[] rangoFechas = {
				new RangoFechas(LocalDate.of(2025, 7, 1), LocalDate.of(2025, 7, 3)),
				new RangoFechas(LocalDate.of(2025, 7, 4), LocalDate.of(2025, 7, 6)),
				new RangoFechas(LocalDate.of(2025, 7, 7), LocalDate.of(2025, 7, 9)),
				new RangoFechas(LocalDate.of(2025, 7, 10), LocalDate.of(2025, 7, 12)),
				new RangoFechas(LocalDate.of(2025, 7, 13), LocalDate.of(2025, 7, 15)),
		};

		final Reserva[] reservas = {
				new Reserva(huespedes[0], 3, alojamientos[0], rangoFechas[0], 7000),
				new Reserva(huespedes[1], 3, alojamientos[0], rangoFechas[1], 7000),
				new Reserva(huespedes[2], 3, alojamientos[0], rangoFechas[2], 7000),
				new Reserva(huespedes[1], 3, alojamientos[0], rangoFechas[3], 7000),
				new Reserva(huespedes[2], 3, alojamientos[0], rangoFechas[4], 7000),
		};

		for (final Reserva reserva : reservas)
			reservaRepository.save(reserva);

		System.out.println("=== Datos cargados correctamente ===");
	}

}
