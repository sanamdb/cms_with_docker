package com.learning.college.ExamService.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamSubmissionResponse {
	
	private String message;
	private LocalDateTime time;

}
