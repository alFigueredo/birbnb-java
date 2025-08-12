package com.panki.birbnb_backend.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.panki.birbnb_backend.model.Usuario;
import com.panki.birbnb_backend.model.Notificacion;
import com.panki.birbnb_backend.service.UsuarioService;

import jakarta.validation.constraints.NotNull;

@Controller
@RequestMapping(path = "/api/usuarios")
public class UsuarioController {

	private final UsuarioService usuarioService;

	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@GetMapping
	public @ResponseBody List<Usuario> getAllUsuarios() {
		return usuarioService.getAll();
	}

	@GetMapping(path = "/{userId}")
	public @ResponseBody Usuario getUsuarioById(@PathVariable Long userId) {
		@NotNull
		final Usuario usuario = usuarioService.getById(userId);
		return usuario;
	}

	@GetMapping(path = "/{userId}/notificaciones")
	public @ResponseBody List<Notificacion> getNotificaciones(@PathVariable Long userId) {
		return usuarioService.getNotificaciones(userId);
	}

	@GetMapping(path = "/{userId}/notificaciones/leidas")
	public @ResponseBody List<Notificacion> getNotificacionesLeida(@PathVariable Long userId) {
		return usuarioService.getNotificacionesLeidas(userId);
	}

	@GetMapping(path = "/{userId}/notificaciones/sinleer")
	public @ResponseBody List<Notificacion> getNotificacionesSinLeer(@PathVariable Long userId) {
		return usuarioService.getNotificacionesSinLeer(userId);
	}

}
