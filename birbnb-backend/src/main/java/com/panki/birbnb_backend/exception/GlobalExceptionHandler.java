package com.panki.birbnb_backend.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<String> manejarNotFoundException(NotFoundException err) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err.getMessage());
	}

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<String> manejarValidationException(ValidationException err) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.getMessage());
	}

	@ExceptionHandler(ConflictException.class)
	public ResponseEntity<String> manejarConflictException(ConflictException err) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body(err.getMessage());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> manejarGenericException(Exception err) {
		logger.error("Error en la API", err);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body("Ocurrio un error inesperado");
	}

}
