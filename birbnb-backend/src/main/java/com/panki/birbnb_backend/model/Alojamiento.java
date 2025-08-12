package com.panki.birbnb_backend.model;

import com.panki.birbnb_backend.model.enums.Caracteristica;
import com.panki.birbnb_backend.model.enums.Moneda;

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
public class Alojamiento {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	// @ManyToOne(fetch = FetchType.EAGER)
	// @JoinColumn(name = "usuario_id")
	// private final Usuario anfitrion;
	// private final String descripcion;
	// private final float precioPorNoche;
	// @Enumerated(EnumType.STRING)
	// private final Moneda moneda;
	// private final String horarioCheckIn;
	// private final String horarioCheckOut;
	// // private final Direccion direccion;
	// private final int cantHuespedesMax;
	// private final Caracteristica[] caracteristicas;
}
