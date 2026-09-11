package com.learning.college.CollegeManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampusDepartmentRequestDTO {
	
	private String campusId;
	private String departmentId;
	private String headOfDepartment;
	private long fees;
	private String buildingBlock;
	private String contactEmail;
	private String phoneNumber;
	private int intakeCapacity;

}
