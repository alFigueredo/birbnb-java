package com.panki.birbnb_backend.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class RangoFechas {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	// private final LocalDateTime fechaInicio;
	// private final LocalDateTime fechaFin;

}
