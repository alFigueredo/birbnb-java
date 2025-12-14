package com.panki.birbnb_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.panki.birbnb_backend.exception.ConflictException;
import com.panki.birbnb_backend.exception.NotFoundException;
import com.panki.birbnb_backend.model.Notificacion;
import com.panki.birbnb_backend.model.Reserva;
import com.panki.birbnb_backend.model.Usuario;
import com.panki.birbnb_backend.model.enums.TipoUsuario;
import com.panki.birbnb_backend.repository.ReservaRepository;
import com.panki.birbnb_backend.repository.UsuarioRepository;

@Service
public class UsuarioService {

	private final UsuarioRepository usuarioRepository;
	private final ReservaRepository reservaRepository;

	public UsuarioService(UsuarioRepository usuarioRepository, ReservaRepository reservaRepository) {
		this.usuarioRepository = usuarioRepository;
		this.reservaRepository = reservaRepository;
	}

	public List<Usuario> getAll() {
		return usuarioRepository.findAll();
	}

	public Usuario getById(Long id) {
		return usuarioRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("El usuario " + id + " no existe"));
	}

	public List<Notificacion> getNotificaciones(Long id) {
		final Usuario usuario = getById(id);
		return usuario.getNotificaciones();
	}

	public List<Notificacion> obtenerNotificacionesLeidas(Long id) {
		final Usuario usuario = getById(id);
		return usuario.obtenerNotificacionesLeidas();
	}

	public List<Notificacion> obtenerNotificacionesSinLeer(Long id) {
		final Usuario usuario = getById(id);
		return usuario.obtenerNotificacionesSinLeer();
	}

	public List<Reserva> getReservas(Long id) {
		final Usuario usuario = getById(id);
		return usuario.getReservas();
	}

	public List<Reserva> getReservasAnfitrion(Long id) {
		final Usuario usuario = getById(id);
		if (!usuario.getTipo().equals(TipoUsuario.ANFITRION))
			throw new ConflictException("El usuario " + usuario.getNombre() + " no es un anfitrión");
		return reservaRepository.findAll().stream()
				.filter(reserva -> reserva.getAnfitrionId().equals(usuario.getId())).toList();
	}

}
