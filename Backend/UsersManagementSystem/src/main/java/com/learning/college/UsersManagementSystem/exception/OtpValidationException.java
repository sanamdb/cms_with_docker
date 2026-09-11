package com.learning.college.UsersManagementSystem.exception;

public class OtpValidationException extends Exception {
	
	private String description;
	
	public OtpValidationException(String message, String description) {
		super(message);
		this.description = description;
	}

	public String getDescription() {
		return description;
	}


}
