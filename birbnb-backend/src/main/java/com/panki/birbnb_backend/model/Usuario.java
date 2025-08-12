package com.panki.birbnb_backend.model;

import com.panki.birbnb_backend.model.enums.TipoUsuario;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private final String nombre;
	private final String email;
	@Enumerated(EnumType.STRING)
	private final TipoUsuario tipo;

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

}
