package com.learning.college.StudentService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.college.StudentService.entity.AdmissionApplication;

public interface AdmissionApplicationRepository extends JpaRepository<AdmissionApplication, String> {

	AdmissionApplication findByUsernameAndDepartmentIdAndBatch(String username, String departmentId, String batch);
	
	List<AdmissionApplication> findByUsername(String username);
	
}
