package com.panki.birbnb_backend.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.panki.birbnb_backend.model.Reserva;
import com.panki.birbnb_backend.service.UsuarioService;

@Controller
@RequestMapping(path = "/api/huespedes")
public class HuespedController {

	private final UsuarioService usuarioService;

	public HuespedController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@GetMapping(path = "/{userId}/reservas")
	public @ResponseBody List<Reserva> getReservas(@PathVariable Long userId) {
		return usuarioService.getReservas(userId);
	}
}
