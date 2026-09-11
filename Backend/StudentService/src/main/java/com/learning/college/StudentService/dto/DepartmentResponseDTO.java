package com.learning.college.StudentService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentResponseDTO {
	
	private String id; 
	private String code; 
	private String name;  
	private String description; 
	private int duration; 
	private String termType;
	
}
