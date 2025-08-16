package com.panki.birbnb_backend.exception;

public class ConflictException extends RuntimeException {

	public ConflictException(String message) {
		super(message);
	}

	public ConflictException() {
		super("Conflicto con el recurso existente");
	}

}
