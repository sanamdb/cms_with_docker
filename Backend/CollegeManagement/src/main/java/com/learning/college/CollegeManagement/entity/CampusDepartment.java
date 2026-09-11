package com.learning.college.CollegeManagement.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CampusDepartment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
	private String id;
	private String campusId;
	private String departmentId;
	private String headOfDepartment;
	private long fees;
	private String buildingBlock;
	private String contactEmail;
	private String phoneNumber;
	private int intakeCapacity;
	private String status;
	private LocalDate establishedDate;

}
