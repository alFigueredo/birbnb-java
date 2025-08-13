package com.panki.birbnb_backend.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.panki.birbnb_backend.model.Alojamiento;
import com.panki.birbnb_backend.service.AlojamientoService;

@Controller
@RequestMapping(path = "/api/alojamientos")
public class AlojamientoController {

	private final AlojamientoService alojamientoService;

	public AlojamientoController(AlojamientoService alojamientoService) {
		this.alojamientoService = alojamientoService;
	}

	@GetMapping
	public @ResponseBody List<Alojamiento> getAllAlojamientos() {
		return alojamientoService.getAll();
	}

	@GetMapping(path = "/{notifId}")
	public @ResponseBody Alojamiento getAlojamientoById(@PathVariable Long notifId) {
		return alojamientoService.getById(notifId);
	}

}
