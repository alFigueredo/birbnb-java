package com.panki.birbnb_backend.exception;

public class NotFoundException extends RuntimeException {

	public NotFoundException(Long id) {
		super("Recurso no encontrado");
	}

}
