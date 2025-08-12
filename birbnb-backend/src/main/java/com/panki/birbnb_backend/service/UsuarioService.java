package com.panki.birbnb_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.panki.birbnb_backend.model.Usuario;
import com.panki.birbnb_backend.repository.UsuarioRepository;
import com.panki.birbnb_backend.model.Notificacion;
import com.panki.birbnb_backend.repository.NotificacionRepository;

@Service
public class UsuarioService {

	private final UsuarioRepository usuarioRepository;
	private final NotificacionRepository notificacionRepository;

	public UsuarioService(UsuarioRepository usuarioRepository, NotificacionRepository notificacionRepository) {
		this.usuarioRepository = usuarioRepository;
		this.notificacionRepository = notificacionRepository;
	}

	public List<Usuario> getAll() {
		return usuarioRepository.findAll();
	}

	public Usuario getById(Long id) {
		return usuarioRepository.findById(id).get();
	}

	public List<Notificacion> getNotificaciones(Long id) {
		final Usuario usuario = getById(id);
		return notificacionRepository.findByUsuario(usuario);
	}

	public List<Notificacion> getNotificacionesLeidas(Long id) {
		final Usuario usuario = getById(id);
		return notificacionRepository.findByUsuarioAndLeida(usuario, true);
	}

	public List<Notificacion> getNotificacionesSinLeer(Long id) {
		final Usuario usuario = getById(id);
		return notificacionRepository.findByUsuarioAndLeida(usuario, false);
	}

}
