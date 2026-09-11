package com.learning.college.CollegeManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.college.CollegeManagement.entity.Campus;

@Repository
public interface  CampusRepo extends JpaRepository<Campus, String>{
	
	public List<Campus> findByCity(String city);
}
