package com.nexscend.employee.management.config;


public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1;

	public ResourceNotFoundException(String message) {
		super(message);
	}

	public ResourceNotFoundException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
