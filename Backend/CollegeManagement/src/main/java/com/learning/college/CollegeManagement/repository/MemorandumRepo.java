package com.learning.college.CollegeManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.college.CollegeManagement.entity.Memorandum;

@Repository
public interface MemorandumRepo extends JpaRepository<Memorandum, String> {

}
