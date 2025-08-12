package com.panki.birbnb_backend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.panki.birbnb_backend.model.Usuario;
import com.panki.birbnb_backend.model.enums.TipoUsuario;
import com.panki.birbnb_backend.repository.NotificacionRepository;
import com.panki.birbnb_backend.repository.UsuarioRepository;

@Component
public class DataLoader implements CommandLineRunner {

	private final UsuarioRepository usuarioRepository;
	private final NotificacionRepository notificacionRepository;

	public DataLoader(UsuarioRepository usuarioRepository, NotificacionRepository notificacionRepository) {
		this.usuarioRepository = usuarioRepository;
		this.notificacionRepository = notificacionRepository;
	}

	@Override
	public void run(String... args) throws Exception {

		System.out.println("=== Precargando datos de prueba ===");

		notificacionRepository.deleteAll();
		usuarioRepository.deleteAll();

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

		System.out.println("=== Datos cargados correctamente ===");
	}

}
