package com.panki.birbnb_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.panki.birbnb_backend.dto.MotivoDTO;
import com.panki.birbnb_backend.dto.ReservaDTO;
import com.panki.birbnb_backend.exception.ConflictException;
import com.panki.birbnb_backend.exception.NotFoundException;
import com.panki.birbnb_backend.model.Alojamiento;
import com.panki.birbnb_backend.model.FactoryNotificacion;
import com.panki.birbnb_backend.model.Reserva;
import com.panki.birbnb_backend.model.Usuario;
import com.panki.birbnb_backend.model.enums.EstadoReserva;
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

		if (!reservaDTO.getRangoFechas().antesDeFechaInicio())
			throw new ConflictException("La fecha de inicio de la reserva ya pasó");

		if (!alojamiento.estasDisponibleEn(reservaDTO.getRangoFechas(), null))
			throw new ConflictException(
					"El alojamiento " + alojamiento.getNombre() + " no esta disponible en las fechas seleccionadas");

		if (!alojamiento.puedenAlojarse(reservaDTO.getCantHuespedes()))
			throw new ConflictException("Cantidad de huéspedes no permitida");

		final Reserva reserva = new Reserva(huespedReservador, reservaDTO.getCantHuespedes(), alojamiento,
				reservaDTO.getRangoFechas());

		FactoryNotificacion.generarNotificacion(reserva, "");
		final Reserva nuevaReserva = reservaRepository.save(reserva);

		return nuevaReserva;
	}

	public Reserva cancelarReserva(Long id, MotivoDTO motivoDTO) {
		final Reserva reserva = getById(id);
		if (!reserva.getRangoFechas().antesDeFechaInicio())
			throw new ConflictException("Fecha límite de cancelación superada");
		reserva.actualizarEstado(EstadoReserva.CANCELADA);
		FactoryNotificacion.generarNotificacion(reserva, motivoDTO.getMotivo());
		final Reserva nuevaReserva = reservaRepository.save(reserva);
		return nuevaReserva;
	}

	public Reserva confirmarReserva(Long id) {
		final Reserva reserva = getById(id);
		if (!reserva.getRangoFechas().antesDeFechaInicio())
			throw new ConflictException("Fecha límite de confirmación superada");
		reserva.actualizarEstado(EstadoReserva.CONFIRMADA);
		FactoryNotificacion.generarNotificacion(reserva, "");
		final Reserva nuevaReserva = reservaRepository.save(reserva);
		return nuevaReserva;
	}

	public Reserva rechazarReserva(Long id, MotivoDTO motivoDTO) {
		final Reserva reserva = getById(id);
		if (!reserva.getRangoFechas().antesDeFechaInicio())
			throw new ConflictException("Fecha límite de rechazo superada");
		reserva.actualizarEstado(EstadoReserva.RECHAZADA);
		FactoryNotificacion.generarNotificacion(reserva, motivoDTO.getMotivo());
		final Reserva nuevaReserva = reservaRepository.save(reserva);
		return nuevaReserva;
	}

	public Reserva modificarReserva(Long id, ReservaDTO reservaDTO) {
		final Reserva reserva = getById(id);
		final Alojamiento alojamiento = alojamientoService.getById(reservaDTO.getAlojamientoId());

		if (reserva.getRangoFechas().equals(reservaDTO.getRangoFechas()) &&
				reserva.getCantHuespedes() == reservaDTO.getCantHuespedes())
			return reserva;
		if (!reservaDTO.getRangoFechas().antesDeFechaInicio())
			throw new ConflictException("La fecha de inicio de la reserva ya pasó");

		if (!alojamiento.estasDisponibleEn(reservaDTO.getRangoFechas(), reserva.getId()))
			throw new ConflictException(
					"El alojamiento " + alojamiento.getNombre() + " no esta disponible en las fechas seleccionadas");

		reserva.setRangoFechas(reservaDTO.getRangoFechas());

		if (!alojamiento.puedenAlojarse(reservaDTO.getCantHuespedes()))
			throw new ConflictException("Cantidad de huéspedes no permitida");

		reserva.setCantHuespedes(reservaDTO.getCantHuespedes());

		reserva.actualizarEstado(EstadoReserva.PENDIENTE);
		FactoryNotificacion.generarNotificacion(reserva, "");
		final Reserva nuevaReserva = reservaRepository.save(reserva);
		return nuevaReserva;
	}

}
