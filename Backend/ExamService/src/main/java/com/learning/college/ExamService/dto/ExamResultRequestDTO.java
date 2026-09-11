package com.learning.college.ExamService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamResultRequestDTO {

	private String examId;
	private String username;
	
}
