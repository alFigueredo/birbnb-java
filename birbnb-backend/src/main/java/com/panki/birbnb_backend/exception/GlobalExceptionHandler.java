package com.panki.birbnb_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(NotFoundError.class)
	public ResponseEntity<String> manejarNotFoundError(NotFoundError err) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err.getMessage());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> manejarGenericError(Exception err) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body("Ocurrio un error inesperado");
	}

}
