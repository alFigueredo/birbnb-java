package com.panki.birbnb_backend.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.panki.birbnb_backend.model.enums.TipoUsuario;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private final String nombre;
	private final String email;
	@Enumerated(EnumType.STRING)
	private final TipoUsuario tipo;

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonManagedReference
	private final List<Notificacion> notificaciones = new ArrayList<>();

	protected Usuario() {
		this.nombre = null;
		this.email = null;
		this.tipo = null;
	}

	public Usuario(String nombre, String email, TipoUsuario tipo) {
		this.nombre = nombre;
		this.email = email;
		this.tipo = tipo;
	}

	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getEmail() {
		return email;
	}

	public TipoUsuario getTipo() {
		return tipo;
	}

	public List<Notificacion> getNotificaciones() {
		return notificaciones;
	}

	public List<Notificacion> obtenerNotificacionesLeidas() {
		return getNotificaciones().stream().filter(Notificacion::isLeida).toList();
	}

	public List<Notificacion> obtenerNotificacionesSinLeer() {
		return getNotificaciones().stream().filter(Notificacion::estaSinLeer).toList();
	}

	public void agregarNotificacion(String mensaje) {
		final Notificacion notificacion = new Notificacion(mensaje, this);
		notificaciones.add(notificacion);
	}

}
