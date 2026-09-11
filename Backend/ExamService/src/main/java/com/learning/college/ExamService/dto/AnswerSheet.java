package com.learning.college.ExamService.dto;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class AnswerSheet {
	
	private String questionId;
	private String selectedAnswer;
	
}