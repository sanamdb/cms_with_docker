package com.learning.college.ExamService.dto;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.ElementCollection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamAnswerSheetDTO {
	
	private String examId;
	private List<AnswerSheet> answerSheet;

}
