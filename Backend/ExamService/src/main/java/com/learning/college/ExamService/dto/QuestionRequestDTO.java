package com.learning.college.ExamService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionRequestDTO {
	
	private String departmentId;
	private String batch;
	private String question;
	private String subject; // like Java, Physic, English (subject)
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	private String answer;  // right answer, not option because ui can flip/shuffle option 

}
