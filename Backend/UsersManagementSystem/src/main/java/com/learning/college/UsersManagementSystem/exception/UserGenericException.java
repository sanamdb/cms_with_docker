package com.learning.college.UsersManagementSystem.exception;

public class UserGenericException extends Exception {
	
	private String description;
	
	public UserGenericException(String message, String description) {
		super(message);
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
