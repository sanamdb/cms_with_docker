package com.learning.college.StudentService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ApplyForAdmissionRequestDTO {
	
	private String departmentId;
	private String batch;
	private String action; //SAVE, SUBMIT, WIDTHRAW

}
