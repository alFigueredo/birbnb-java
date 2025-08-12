package com.panki.birbnb_backend.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.panki.birbnb_backend.model.Notificacion;
import com.panki.birbnb_backend.service.NotificacionService;

@Controller
@RequestMapping(path = "/api/notificaciones")
public class NotificacionController {

	private final NotificacionService notificacionService;

	public NotificacionController(NotificacionService notificacionService) {
		this.notificacionService = notificacionService;
	}

	@GetMapping
	public @ResponseBody List<Notificacion> getAllNotificaciones() {
		return notificacionService.getAll();
	}

	@GetMapping(path = "/{notifId}")
	public @ResponseBody Notificacion getNotificacionById(@PathVariable Long notifId) {
		return notificacionService.getById(notifId);
	}

}
