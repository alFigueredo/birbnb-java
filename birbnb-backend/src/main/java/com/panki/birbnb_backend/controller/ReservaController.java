package com.panki.birbnb_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.panki.birbnb_backend.dto.MotivoDTO;
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
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody Reserva postReserva(@RequestBody ReservaDTO reservaDTO) {
		return reservaService.post(reservaDTO);
	}

	@PutMapping(path = "/{resId}/cancelar")
	public @ResponseBody Reserva cancelarReserva(@PathVariable Long resId, @RequestBody MotivoDTO motivoDTO) {
		return reservaService.cancelarReserva(resId, motivoDTO);
	}

	@PutMapping(path = "/{resId}/confirmar")
	public @ResponseBody Reserva confirmarReserva(@PathVariable Long resId) {
		return reservaService.confirmarReserva(resId);
	}

	@PutMapping(path = "/{resId}/rechazar")
	public @ResponseBody Reserva rechazarReserva(@PathVariable Long resId, @RequestBody MotivoDTO motivoDTO) {
		return reservaService.rechazarReserva(resId, motivoDTO);
	}

	@PutMapping(path = "/{resId}")
	public @ResponseBody Reserva modificarReserva(@PathVariable Long resId, @RequestBody ReservaDTO reservaDTO) {
		return reservaService.modificarReserva(resId, reservaDTO);
	}

}
