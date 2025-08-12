package com.panki.birbnb_backend.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Notificacion {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private final String mensaje;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario_id")
	private final Usuario usuario;
	private final LocalDateTime fechaAlta;
	private final boolean leida = false;
	private final LocalDateTime fechaLeida = null;

	protected Notificacion() {
		this.mensaje = null;
		this.usuario = null;
		this.fechaAlta = null;
	}

	public Notificacion(String mensaje, Usuario usuario) {
		this.mensaje = mensaje;
		this.usuario = usuario;
		this.fechaAlta = LocalDateTime.now();
	}

	public Long getId() {
		return id;
	}

	public String getMensaje() {
		return mensaje;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public LocalDateTime getFechaAlta() {
		return fechaAlta;
	}

	public boolean isLeida() {
		return leida;
	}

	public LocalDateTime getFechaLeida() {
		return fechaLeida;
	}

}
