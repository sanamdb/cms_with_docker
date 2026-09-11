package com.learning.college.StudentService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BatchResponseDTO {
	
	private String id;
	private String academicYear;
	private String status; // NOTHING, ADMISSION, STARTED, COMPLETED

}
