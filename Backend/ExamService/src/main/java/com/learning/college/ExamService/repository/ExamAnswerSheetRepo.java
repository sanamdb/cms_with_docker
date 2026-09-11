package com.learning.college.ExamService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.college.ExamService.entity.ExamAnswerSheet;

@Repository
public interface ExamAnswerSheetRepo extends JpaRepository<ExamAnswerSheet, String>{
	public ExamAnswerSheet findByUsernameAndExamId(String username, String examId);
}
