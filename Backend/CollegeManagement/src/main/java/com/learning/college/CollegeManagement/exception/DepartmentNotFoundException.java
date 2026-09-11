package com.learning.college.CollegeManagement.exception;

public class DepartmentNotFoundException extends Exception {
	
	private String description;
	
	public DepartmentNotFoundException(String message, String description) {
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
