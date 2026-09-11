package com.learning.college.ExamService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.college.ExamService.entity.Examination;

@Repository
public interface ExaminationRepository extends JpaRepository<Examination, String>{
	
	Examination findByDepartmentIdAndBatch(String departmentId, String batch);

}
