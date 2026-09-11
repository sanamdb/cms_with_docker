package com.learning.college.ExamService.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamPaperResponseDTO {
	
	private String id;
	private String examName;
	private String departmentId;
	private String batch;
	private LocalDateTime startDateTime;
	private LocalDateTime endDateTime;
	private String description;
	private Map<String, List<QuestionPaperResponseDTO>> questions;

}
