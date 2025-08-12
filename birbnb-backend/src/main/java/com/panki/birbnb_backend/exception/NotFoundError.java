package com.panki.birbnb_backend.exception;

public class NotFoundError extends RuntimeException {

	public NotFoundError(Long id) {
		super("Recurso no encontrado");
	}

}
