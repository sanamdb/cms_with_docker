package com.learning.college.CollegeManagement.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampusDepartmentFullResponseDTO {
	
	private CampusResponseDTO campus;
	private List<DepartmentForCampus> campusDepartment;
	

}
