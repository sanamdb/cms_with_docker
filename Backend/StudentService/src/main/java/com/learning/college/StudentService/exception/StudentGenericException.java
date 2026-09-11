package com.learning.college.StudentService.exception;

public class StudentGenericException extends Exception {
	
	private String description;
	
	public StudentGenericException(String message, String description) {
		super(message);
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
}
