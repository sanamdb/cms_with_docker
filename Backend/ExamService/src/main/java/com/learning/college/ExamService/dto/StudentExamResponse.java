package com.learning.college.ExamService.dto;

import com.learning.college.ExamService.entity.ExamAnswerSheet;
import com.learning.college.ExamService.entity.ExamResult;
import com.learning.college.ExamService.entity.Examination;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentExamResponse {
	
	private Examination exam;
	private ExamAnswerSheet submission;
	private ExamResult result;

}
