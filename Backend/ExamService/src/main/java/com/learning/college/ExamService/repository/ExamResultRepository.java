package com.learning.college.ExamService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.college.ExamService.entity.ExamResult;

@Repository
public interface ExamResultRepository extends JpaRepository<ExamResult, String>{

	ExamResult findByUsernameAndExamId(String username, String examId);
}
