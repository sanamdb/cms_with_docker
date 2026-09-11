package com.learning.college.ExamService.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.learning.college.ExamService.dto.AnswerSheet;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
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
public class ExamAnswerSheet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
	private String id;
	private String username;
	private String examId;
	private LocalDateTime submissionDateTime;
	
	@ElementCollection
	private List<AnswerSheet> answerSheet;
	

}
