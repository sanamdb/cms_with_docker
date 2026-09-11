package com.learning.college.ExamService.feign;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.learning.college.ExamService.dto.QuestionRequestDTO;
import com.learning.college.ExamService.dto.QuestionResponseDTO;

@Component
@FeignClient(name="QUESTION-SERVICE", path = "question/api/v1")
public interface QuestionFeignClient {
	
	@PostMapping("question")
	public ResponseEntity<QuestionResponseDTO> createQuestion(@RequestBody QuestionRequestDTO requestPayload);
	
	@PutMapping("question/{id}")
	public ResponseEntity<QuestionResponseDTO> updateQuestion(@PathVariable("id") String id, @RequestBody QuestionRequestDTO requestPayload);
	
	@GetMapping("question")
	public ResponseEntity<List<QuestionResponseDTO>> getAllQuestion();
	
	@GetMapping("question/{id}")
	public ResponseEntity<QuestionResponseDTO> getQuestionById(@PathVariable("id") String id);
	
	@DeleteMapping("question/{id}")
	public ResponseEntity<QuestionResponseDTO> deleteQuestionById(@PathVariable("id") String id);
	
	@GetMapping("question/batch/department/subject")
	public ResponseEntity<List<QuestionResponseDTO>> getQuestionsByBatchAndDepartmentAndSubject(@RequestParam("batch") String batch, @RequestParam("departmentId") String departmentId, @RequestParam("subject") String subject);
		
	@GetMapping("question/batch/department")
	public ResponseEntity<List<QuestionResponseDTO>> getQuestionsByBatchAndDepartment(@RequestParam("batch") String batch, @RequestParam("departmentId") String departmentId);
		
	@GetMapping("question/batch/department/subject/exam")
	public ResponseEntity<List<String>> getQuestionsIdForExam(@RequestParam("batch") String batch, @RequestParam("departmentId") String departmentId, @RequestParam("subject") String subject);
		
}
