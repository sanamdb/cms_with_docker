package com.learning.college.CollegeManagement.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampusDepartmentDetailsResponseDTO {
	
	private String campusId;
	private String departmentId;
	private String headOfDepartment;
	private long fees;
	private String buildingBlock;
	private String contactEmail;
	private String phoneNumber;
	private int intakeCapacity;
	private LocalDate establishedDate;
	private String campusName;
	private String campusCity;
	private String campusAddress;
	private String departmentCode;
	private String departmentName;
	private String departmentDescription;
	private int duration;
	private String termType;

}
