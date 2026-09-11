package com.learning.college.ExamService.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamRequestDTO {

	private String examName;
	private String departmentId;
	private String batch;
	private LocalDateTime startDateTime;
	private LocalDateTime endDateTime;
	private String description;
	private List<String> subjects;
	
}
