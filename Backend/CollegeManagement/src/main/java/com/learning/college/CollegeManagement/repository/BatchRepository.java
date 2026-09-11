package com.learning.college.CollegeManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.college.CollegeManagement.entity.Batch;

@Repository
public interface BatchRepository extends JpaRepository<Batch, String>{

	Batch findByAcademicYear(String year);
}
