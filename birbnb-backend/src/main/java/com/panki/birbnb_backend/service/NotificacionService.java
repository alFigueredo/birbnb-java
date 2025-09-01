package com.panki.birbnb_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.panki.birbnb_backend.exception.NotFoundException;
import com.panki.birbnb_backend.model.Notificacion;
import com.panki.birbnb_backend.repository.NotificacionRepository;

@Service
public class NotificacionService {

	private final NotificacionRepository notificacionRepository;

	public NotificacionService(NotificacionRepository notificacionRepository) {
		this.notificacionRepository = notificacionRepository;
	}

	public List<Notificacion> getAll() {
		return notificacionRepository.findAll();
	}

	public Notificacion getById(Long id) {
		return notificacionRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("La notificación " + id + " no existe"));
	}

	public Notificacion marcarComoLeida(Long id) {
		final Notificacion notificacion = getById(id);
		notificacion.marcarComoLeida();
		return notificacionRepository.save(notificacion);
	}

}
