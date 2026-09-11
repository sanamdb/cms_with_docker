package com.learning.college.StudentService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.college.StudentService.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

}
