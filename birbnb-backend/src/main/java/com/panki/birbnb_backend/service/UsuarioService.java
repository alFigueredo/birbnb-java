package com.panki.birbnb_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.panki.birbnb_backend.exception.NotFoundException;
import com.panki.birbnb_backend.model.Notificacion;
import com.panki.birbnb_backend.model.Usuario;
import com.panki.birbnb_backend.repository.UsuarioRepository;

@Service
public class UsuarioService {

	private final UsuarioRepository usuarioRepository;

	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
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

}
