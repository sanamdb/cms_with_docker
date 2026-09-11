package com.learning.college.QuestionService.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Question {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
	private String id;
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
