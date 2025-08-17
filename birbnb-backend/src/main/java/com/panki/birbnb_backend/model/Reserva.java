package com.panki.birbnb_backend.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.panki.birbnb_backend.model.enums.EstadoReserva;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Reserva {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private LocalDateTime fechaAlta;
	private LocalDateTime fechaActualizacion;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "huespedReservador_id")
	@JsonBackReference
	private final Usuario huespedReservador;
	private int cantHuespedes;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "alojamiento_id")
	@JsonBackReference
	private final Alojamiento alojamiento;
	@Embedded
	private final RangoFechas rangoFechas;
	@Enumerated(EnumType.STRING)
	private EstadoReserva estadoReserva = EstadoReserva.PENDIENTE;
	private final float precioPorNoche;

	protected Reserva() {
		this.fechaAlta = null;
		this.fechaActualizacion = null;
		this.huespedReservador = null;
		this.cantHuespedes = 0;
		this.alojamiento = null;
		this.rangoFechas = null;
		this.precioPorNoche = 0;
	}

	public Reserva(Usuario huespedReservador, int cantHuespedes, Alojamiento alojamiento,
			RangoFechas rangoFechas) {
		this.fechaAlta = LocalDateTime.now();
		this.fechaActualizacion = getFechaAlta();
		this.huespedReservador = huespedReservador;
		this.cantHuespedes = cantHuespedes;
		this.alojamiento = alojamiento;
		rangoFechas.esValido();
		this.rangoFechas = rangoFechas;
		this.precioPorNoche = alojamiento.getPrecioPorNoche();
	}

	public Long getId() {
		return id;
	}

	public LocalDateTime getFechaAlta() {
		return fechaAlta;
	}

	public LocalDateTime getFechaActualizacion() {
		return fechaActualizacion;
	}

	public Usuario getHuespedReservador() {
		return huespedReservador;
	}

	public Long getHuespedReservadorId() {
		return getHuespedReservador().getId();
	}

	public int getCantHuespedes() {
		return cantHuespedes;
	}

	public Alojamiento getAlojamiento() {
		return alojamiento;
	}

	public Long getAlojamientoId() {
		return getAlojamiento().getId();
	}

	public RangoFechas getRangoFechas() {
		return rangoFechas;
	}

	public EstadoReserva getEstadoReserva() {
		return estadoReserva;
	}

	public float getPrecioPorNoche() {
		return precioPorNoche;
	}

	private void updateFechaActualizacion() {
		this.fechaActualizacion = LocalDateTime.now();
	}

	public void setCantHuespedes(int cantHuespedes) {
		this.cantHuespedes = cantHuespedes;
	}

	public void setRangoFechas(RangoFechas rangoFechas) {
		rangoFechas.esValido();
		getRangoFechas().modificarRangoFechas(rangoFechas);
	}

	public void actualizarEstado(EstadoReserva estadoReserva) {
		if (estadoReserva == EstadoReserva.PENDIENTE)
			updateFechaActualizacion();
		this.estadoReserva = estadoReserva;
	}

	public boolean reservaAnulada() {
		return getEstadoReserva() == EstadoReserva.CANCELADA || getEstadoReserva() == EstadoReserva.RECHAZADA;
	}

	public boolean reservaRespondidaPorAnfitrion() {
		return getEstadoReserva() == EstadoReserva.CONFIRMADA || getEstadoReserva() == EstadoReserva.RECHAZADA;
	}

	public boolean haySolapamiento(RangoFechas rangoFechas) {
		return !reservaAnulada() && getRangoFechas().haySolapamiento(rangoFechas);
	}

}
