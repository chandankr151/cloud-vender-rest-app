package com.chandan.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CloudVenderExceptionHandler {

	@ExceptionHandler(value = { CloudVendorNotFoundException.class })
	public ResponseEntity<Object> handleCloudVenderNotFoundException(
			CloudVendorNotFoundException cloudVenderNotFoundException) {

		CloudVenderException cloudVenderException = new CloudVenderException(cloudVenderNotFoundException.getMessage(),
				cloudVenderNotFoundException.getCause(), HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(cloudVenderException, HttpStatus.NOT_FOUND);
	}
}
