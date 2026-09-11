package com.learning.college.CollegeManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BatchRequestDTO {
	
	private String academicYear;
	private String status; // NOTHING, ADMISSION, STARTED, COMPLETED

}
