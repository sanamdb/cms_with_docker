package com.learning.college.StudentService.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "admission_applications")
public class AdmissionApplication {

	@Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
	private String username;
	private String departmentId;
	private String batch;
	private String status; //Save, Submitted
	private String remarks; // accepted, rejected, failed
	private LocalDateTime createDateTime;
	private LocalDateTime updateDateTime;
	
	
	
}

/*
 * Apply 
 * create batch for department 
 * admission exam 
 * question 
 * then batch cache
 * */
