package com.panki.birbnb_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

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
		return notificacionRepository.findById(id).get();
	}

}
