package com.learning.college.CollegeManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentForCampus {
	
	private CampusDepartmentResponseDTO offerDepartment;
	private DepartmentResponseDTO department;
	
}
