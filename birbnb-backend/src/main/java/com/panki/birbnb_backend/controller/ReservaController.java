package com.panki.birbnb_backend.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.panki.birbnb_backend.dto.ReservaDTO;
import com.panki.birbnb_backend.model.Reserva;
import com.panki.birbnb_backend.service.ReservaService;

@Controller
@RequestMapping(path = "/api/reservas")
public class ReservaController {

	private final ReservaService reservaService;

	public ReservaController(ReservaService reservaService) {
		this.reservaService = reservaService;
	}

	@GetMapping
	public @ResponseBody List<Reserva> getAllReservas() {
		return reservaService.getAll();
	}

	@GetMapping(path = "/{resId}")
	public @ResponseBody Reserva getReservaById(@PathVariable Long resId) {
		return reservaService.getById(resId);
	}

	@PostMapping
	public @ResponseBody Reserva postReserva(@RequestBody ReservaDTO reservaDTO) {
		return reservaService.post(reservaDTO);
	}

}
