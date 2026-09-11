package com.learning.college.StudentService.exception;

public class UsernameNotFoundException extends Exception {
	
	private String description;
	
	public UsernameNotFoundException(String message, String description) {
		super(message);
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
