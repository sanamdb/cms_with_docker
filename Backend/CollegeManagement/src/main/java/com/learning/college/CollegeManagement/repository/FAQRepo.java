package com.learning.college.CollegeManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.college.CollegeManagement.entity.FAQ;

@Repository
public interface FAQRepo extends JpaRepository<FAQ, Integer>{

}
