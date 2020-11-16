package com.example.petstore.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

	private String message;
	/**
	 * 
	 */
	private static final long serialVersionUID = -1606693574360625443L;

	public ResourceNotFoundException() {
		message = "Resource not found";
	}

	public ResourceNotFoundException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
