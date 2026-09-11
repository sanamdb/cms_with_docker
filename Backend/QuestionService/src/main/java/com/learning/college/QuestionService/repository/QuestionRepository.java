package com.learning.college.QuestionService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.learning.college.QuestionService.entity.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, String>{
	
	List<Question> findByBatchAndDepartmentIdAndSubject(String batch, String departmentId, String subject);
	
	List<Question> findByBatchAndDepartmentId(String batch, String departmentId);
	
	@Query("select q.id from Question q where q.batch=:batch and q.departmentId=:departmentId and q.subject=:subject")
	List<String> getTheQuestionForExam(@Param("batch") String batch, @Param("departmentId") String departmentId, @Param("subject") String subject);
	
}
