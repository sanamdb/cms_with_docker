package com.learning.college.CollegeManagement.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.learning.college.CollegeManagement.dto.ExceptionResponseDTO;
import com.learning.college.CollegeManagement.exception.BatchException;
import com.learning.college.CollegeManagement.exception.CampusNotFoundException;
import com.learning.college.CollegeManagement.exception.DepartmentNotFoundException;
import com.learning.college.CollegeManagement.exception.GenericCollegeException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(com.learning.college.CollegeManagement.exception.GenericCollegeException.class)
	public ResponseEntity<ExceptionResponseDTO> genericCollegeException(GenericCollegeException e) {
		ExceptionResponseDTO res = new ExceptionResponseDTO();
		res.setMessage(e.getMessage());
		res.setDescription(e.getDescription());
		res.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@ExceptionHandler(com.learning.college.CollegeManagement.exception.CampusNotFoundException.class)
	public ResponseEntity<ExceptionResponseDTO> campusNotFound(CampusNotFoundException e) {
		ExceptionResponseDTO res = new ExceptionResponseDTO();
		res.setMessage(e.getMessage());
		res.setDescription(e.getDescription());
		res.setStatus(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@ExceptionHandler(com.learning.college.CollegeManagement.exception.DepartmentNotFoundException.class)
	public ResponseEntity<ExceptionResponseDTO> departmentNotFound(DepartmentNotFoundException e) {
		ExceptionResponseDTO res = new ExceptionResponseDTO();
		res.setMessage(e.getMessage());
		res.setDescription(e.getDescription());
		res.setStatus(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@ExceptionHandler(com.learning.college.CollegeManagement.exception.BatchException.class)
	public ResponseEntity<ExceptionResponseDTO> batchException(BatchException e) {
		ExceptionResponseDTO res = new ExceptionResponseDTO();
		res.setMessage(e.getMessage());
		res.setDescription(e.getDescription());
		res.setStatus(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

}
