package com.panki.birbnb_backend.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.panki.birbnb_backend.dto.MotivoDTO;
import com.panki.birbnb_backend.dto.ReservaDTO;
import com.panki.birbnb_backend.exception.ConflictException;
import com.panki.birbnb_backend.exception.NotFoundException;
import com.panki.birbnb_backend.model.Reserva;
import com.panki.birbnb_backend.model.Usuario;
import com.panki.birbnb_backend.model.enums.EstadoReserva;
import com.panki.birbnb_backend.model.Alojamiento;
import com.panki.birbnb_backend.model.FactoryNotificacion;
import com.panki.birbnb_backend.repository.ReservaRepository;

@Service
public class ReservaService {

	private final ReservaRepository reservaRepository;
	private final AlojamientoService alojamientoService;
	private final UsuarioService usuarioService;

	public ReservaService(ReservaRepository reservaRepository, AlojamientoService alojamientoService,
			UsuarioService usuarioService) {
		this.reservaRepository = reservaRepository;
		this.alojamientoService = alojamientoService;
		this.usuarioService = usuarioService;
	}

	public List<Reserva> getAll() {
		return reservaRepository.findAll();
	}

	public Reserva getById(Long id) {
		return reservaRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("La reserva " + id + " no existe"));
	}

	public Reserva post(ReservaDTO reservaDTO) {
		final Alojamiento alojamiento = alojamientoService.getById(reservaDTO.getAlojamientoId());
		final Usuario huespedReservador = usuarioService.getById(reservaDTO.getHuespedReservadorId());

		if (reservaDTO.getRangoFechas().getFechaInicio().isBefore(LocalDate.now()))
			throw new ConflictException("La fecha de inicio del alojamiento ya pasó");

		final Reserva reserva = new Reserva(huespedReservador, reservaDTO.getCantHuespedes(), alojamiento,
				reservaDTO.getRangoFechas());

		if (!alojamiento.estasDisponibleEn(reserva))
			throw new ConflictException(
					"El alojamiento " + alojamiento.getNombre() + " no esta disponible en las fechas seleccionadas");

		if (!alojamiento.puedenAlojarse(reserva.getCantHuespedes()))
			throw new ConflictException("Cantidad de huéspedes no permitida");

		FactoryNotificacion.generarNotificacion(reserva, "");
		final Reserva nuevaReserva = reservaRepository.save(reserva);

		return nuevaReserva;
	}

	public Reserva cancelarReserva(Long id, MotivoDTO motivoDTO) {
		final Reserva reserva = getById(id);
		if (!reserva.getRangoFechas().getFechaInicio().isAfter(LocalDate.now()))
			throw new ConflictException("Fecha límite de cancelación superada");
		reserva.actualizarEstado(EstadoReserva.CANCELADA);
		FactoryNotificacion.generarNotificacion(reserva, motivoDTO.getMotivo());
		return reservaRepository.save(reserva);
	}

}
