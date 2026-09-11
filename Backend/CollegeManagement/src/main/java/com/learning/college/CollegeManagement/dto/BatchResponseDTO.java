package com.learning.college.CollegeManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BatchResponseDTO {
	
	private String id;
	private String academicYear;
	private String status; // NOTHING, ADMISSION, STARTED, COMPLETED

}
