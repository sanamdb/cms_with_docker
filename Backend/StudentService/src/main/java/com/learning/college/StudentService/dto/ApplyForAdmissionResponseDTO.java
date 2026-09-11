package com.learning.college.StudentService.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ApplyForAdmissionResponseDTO {

	 	private String id;
		private String departmentId;
		private String batch;
		private String status; 
		private String remarks; 
		private LocalDateTime createDateTime;
		private LocalDateTime updateDateTime;
		
}
