package com.learning.college.CollegeManagement.dto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.college.CollegeManagement.entity.Admission;

@Repository
public interface AdmissionRepository extends JpaRepository<Admission, String>{
	Admission findByStudentUsername(String studentUsername);
}
