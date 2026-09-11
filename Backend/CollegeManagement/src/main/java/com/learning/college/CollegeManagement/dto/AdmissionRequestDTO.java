package com.learning.college.CollegeManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdmissionRequestDTO {
	
	private String studentUsername;
	private String campusId;
	private String departmentId;
	private String batch;
	private String examId;
	
}
