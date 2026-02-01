package com.panki.birbnb_backend.dto;

import com.panki.birbnb_backend.model.Usuario;
import com.panki.birbnb_backend.model.enums.TipoUsuario;

public class UsuarioRequest {

	private final String nombre;
	private final String email;
	private final TipoUsuario tipoUsuario;

	public UsuarioRequest(String name, String email, TipoUsuario tipoUsuario) {
		this.nombre = name;
		this.email = email;
		this.tipoUsuario = tipoUsuario;
	}

	public UsuarioRequest(Usuario usuario) {
		this.nombre = usuario.getNombre();
		this.email = usuario.getEmail();
		this.tipoUsuario = usuario.getTipo();
	}

	public static Usuario from(UsuarioRequest usuarioRequest) {
		return new Usuario(usuarioRequest.getNombre(), usuarioRequest.getEmail(), usuarioRequest.getTipoUsuario());
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
