package com.learning.college.CollegeManagement.exception;

public class CampusNotFoundException extends Exception {
	
	private String description;
	
	public CampusNotFoundException(String message, String description) {
		super(message);
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
