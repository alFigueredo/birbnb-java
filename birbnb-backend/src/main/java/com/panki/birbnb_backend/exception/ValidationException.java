package com.panki.birbnb_backend.exception;

public class ValidationException extends RuntimeException {

	public ValidationException(String message) {
		super(message);
	}

	public ValidationException() {
		super("Datos de validación inválidos");
	}

}
