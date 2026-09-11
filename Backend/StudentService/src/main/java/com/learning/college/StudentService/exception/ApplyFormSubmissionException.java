package com.learning.college.StudentService.exception;

public class ApplyFormSubmissionException extends Exception {

	private String description;
	
	public ApplyFormSubmissionException(String message, String description) {
		super(message);
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
}
