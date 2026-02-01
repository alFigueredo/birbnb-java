package com.panki.birbnb_backend.dto;

import com.panki.birbnb_backend.model.Usuario;
import com.panki.birbnb_backend.model.enums.TipoUsuario;

public class UsuarioResponse {

	private final Long id;
	private final String nombre;
	private final String email;
	private final TipoUsuario tipoUsuario;

	public UsuarioResponse(Long id, String name, String email, TipoUsuario tipoUsuario) {
		this.id = id;
		this.nombre = name;
		this.email = email;
		this.tipoUsuario = tipoUsuario;
	}

	public UsuarioResponse(Usuario usuario) {
		this.id = usuario.getId();
		this.nombre = usuario.getNombre();
		this.email = usuario.getEmail();
		this.tipoUsuario = usuario.getTipo();
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

	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

}
