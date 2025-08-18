package com.panki.birbnb_backend.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.panki.birbnb_backend.dto.FiltrosAlojamientoDTO;
import com.panki.birbnb_backend.model.Alojamiento;
import com.panki.birbnb_backend.service.AlojamientoService;

@Controller
@RequestMapping(path = "/api/alojamientos")
public class AlojamientoController {

	private final AlojamientoService alojamientoService;

	public AlojamientoController(AlojamientoService alojamientoService) {
		this.alojamientoService = alojamientoService;
	}

	@GetMapping(path = "/{notifId}")
	public @ResponseBody Alojamiento getAlojamientoById(@PathVariable Long notifId) {
		return alojamientoService.getById(notifId);
	}

	@GetMapping
	public @ResponseBody Page<Alojamiento> filtrarAlojamientos(
			@RequestParam(required = false) String nombre,
			@RequestParam(required = false) Integer precioGt,
			@RequestParam(required = false) Integer precioLt,
			@RequestParam(required = false) Integer cantHuespedes,
			@RequestParam(required = false) String ciudad,
			@RequestParam(required = false) String pais,
			@PageableDefault(size = 12) Pageable pageable) {
		final FiltrosAlojamientoDTO filtrosAlojamientoDTO = new FiltrosAlojamientoDTO(nombre, precioGt, precioLt,
				cantHuespedes, ciudad, pais);
		return alojamientoService.filtrarAlojamientos(filtrosAlojamientoDTO, pageable);
	}

}
