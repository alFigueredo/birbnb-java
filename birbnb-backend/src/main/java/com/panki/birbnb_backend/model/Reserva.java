package com.panki.birbnb_backend.model;

import java.time.LocalDateTime;

import com.panki.birbnb_backend.model.enums.EstadoReserva;

import jakarta.persistence.Entity;
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
	// private final LocalDateTime fechaAlta;
	// @ManyToOne(fetch = FetchType.EAGER)
	// @JoinColumn(name = "usuario_id")
	// private final Usuario huespedReservador;
	// private final int cantHuespedes;
	// private final Alojamiento alojamiento;
	// private final RangoFechas rangoFechas;
	// private final EstadoReserva estadoReserva;
	// private final float precioPorNoche;

}
