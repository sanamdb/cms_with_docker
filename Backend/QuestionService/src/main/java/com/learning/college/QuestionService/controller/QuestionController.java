package com.learning.college.QuestionService.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learning.college.QuestionService.dto.QuestionRequestDTO;
import com.learning.college.QuestionService.dto.QuestionResponseDTO;
import com.learning.college.QuestionService.entity.Question;
import com.learning.college.QuestionService.service.QuestionService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("question/api/v1")
@Slf4j
public class QuestionController {

	@Autowired
	private QuestionService service;
	
	
	@PostMapping("question")
	public ResponseEntity<QuestionResponseDTO> createQuestion(@RequestBody QuestionRequestDTO requestPayload) {
		
		Question questionPayload = new Question();
		questionPayload.setDepartmentId(requestPayload.getDepartmentId());
		questionPayload.setBatch(requestPayload.getBatch());
		questionPayload.setQuestion(requestPayload.getQuestion());
		questionPayload.setSubject(requestPayload.getSubject());
		questionPayload.setOption1(requestPayload.getOption1());
		questionPayload.setOption2(requestPayload.getOption2());
		questionPayload.setOption3(requestPayload.getOption3());
		questionPayload.setOption4(requestPayload.getOption4());
		questionPayload.setAnswer(requestPayload.getAnswer());
	
		Question questionResponse = service.createQuestion(questionPayload);
		
		QuestionResponseDTO response = new QuestionResponseDTO();
		response.setId(questionResponse.getId());
		response.setDepartmentId(questionResponse.getDepartmentId());
		response.setBatch(questionResponse.getBatch());
		response.setQuestion(questionResponse.getQuestion());
		response.setSubject(questionResponse.getSubject());
		response.setOption1(questionResponse.getOption1());
		response.setOption2(questionResponse.getOption2());
		response.setOption3(questionResponse.getOption3());
		response.setOption4(questionResponse.getOption4());
		response.setAnswer(questionResponse.getAnswer());
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
		
	}
	
	@PutMapping("question/{id}")
	public ResponseEntity<QuestionResponseDTO> updateQuestion(@PathVariable("id") String id, @RequestBody QuestionRequestDTO requestPayload) {
		
		Question questionPayload = new Question();
		questionPayload.setId(id);
		questionPayload.setDepartmentId(requestPayload.getDepartmentId());
		questionPayload.setBatch(requestPayload.getBatch());
		questionPayload.setQuestion(requestPayload.getQuestion());
		questionPayload.setSubject(requestPayload.getSubject());
		questionPayload.setOption1(requestPayload.getOption1());
		questionPayload.setOption2(requestPayload.getOption2());
		questionPayload.setOption3(requestPayload.getOption3());
		questionPayload.setOption4(requestPayload.getOption4());
		questionPayload.setAnswer(requestPayload.getAnswer());
	
		Question questionResponse = service.updateQuestion(questionPayload);
		
		QuestionResponseDTO response = new QuestionResponseDTO();
		response.setId(questionResponse.getId());
		response.setDepartmentId(questionResponse.getDepartmentId());
		response.setBatch(questionResponse.getBatch());
		response.setQuestion(questionResponse.getQuestion());
		response.setSubject(questionResponse.getSubject());
		response.setOption1(questionResponse.getOption1());
		response.setOption2(questionResponse.getOption2());
		response.setOption3(questionResponse.getOption3());
		response.setOption4(questionResponse.getOption4());
		response.setAnswer(questionResponse.getAnswer());
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
		
	}
	
	@GetMapping("question")
	public ResponseEntity<List<QuestionResponseDTO>> getAllQuestion() {
		
		List<Question> questions = service.findAllQuestions();
		
		List<QuestionResponseDTO> response = new ArrayList<>();
		for(Question quest : questions) {
			QuestionResponseDTO question = new QuestionResponseDTO();
			question.setId(quest.getId());
			question.setDepartmentId(quest.getDepartmentId());
			question.setBatch(quest.getBatch());
			question.setQuestion(quest.getQuestion());
			question.setSubject(quest.getSubject());
			question.setOption1(quest.getOption1());
			question.setOption2(quest.getOption2());
			question.setOption3(quest.getOption3());
			question.setOption4(quest.getOption4());
			question.setAnswer(quest.getAnswer());
			response.add(question);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("question/{id}")
	public ResponseEntity<QuestionResponseDTO> getQuestionById(@PathVariable("id") String id) {
		
		Question quest = service.findQuestionById(id);
		
		log.info("Get question by Id from question service");
		
		QuestionResponseDTO question = new QuestionResponseDTO();
		question.setId(quest.getId());
		question.setDepartmentId(quest.getDepartmentId());
		question.setBatch(quest.getBatch());
		question.setQuestion(quest.getQuestion());
		question.setSubject(quest.getSubject());
		question.setOption1(quest.getOption1());
		question.setOption2(quest.getOption2());
		question.setOption3(quest.getOption3());
		question.setOption4(quest.getOption4());
		question.setAnswer(quest.getAnswer());
		
		return new ResponseEntity<>(question, HttpStatus.OK);
	}
	
	@DeleteMapping("question/{id}")
	public ResponseEntity<QuestionResponseDTO> deleteQuestionById(@PathVariable("id") String id) {
		Question quest = service.deleteQuestionById(id);
		
		QuestionResponseDTO question = new QuestionResponseDTO();
		question.setId(quest.getId());
		question.setDepartmentId(quest.getDepartmentId());
		question.setBatch(quest.getBatch());
		question.setQuestion(quest.getQuestion());
		question.setSubject(quest.getSubject());
		question.setOption1(quest.getOption1());
		question.setOption2(quest.getOption2());
		question.setOption3(quest.getOption3());
		question.setOption4(quest.getOption4());
		question.setAnswer(quest.getAnswer());
		
		return new ResponseEntity<>(question, HttpStatus.OK);
	}
	
	@GetMapping("question/batch/department/subject")
	public ResponseEntity<List<QuestionResponseDTO>> getQuestionsByBatchAndDepartmentAndSubject(@RequestParam("batch") String batch, @RequestParam("departmentId") String departmentId, @RequestParam("subject") String subject) {
		
		List<Question> questions = service.findQuestionsByBatchAndDepartmentAndSubject(batch, departmentId, subject);
		
		List<QuestionResponseDTO> response = new ArrayList<>();
		for(Question quest : questions) {
			QuestionResponseDTO question = new QuestionResponseDTO();
			question.setId(quest.getId());
			question.setDepartmentId(quest.getDepartmentId());
			question.setBatch(quest.getBatch());
			question.setQuestion(quest.getQuestion());
			question.setSubject(quest.getSubject());
			question.setOption1(quest.getOption1());
			question.setOption2(quest.getOption2());
			question.setOption3(quest.getOption3());
			question.setOption4(quest.getOption4());
			question.setAnswer(quest.getAnswer());
			response.add(question);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("question/batch/department")
	public ResponseEntity<List<QuestionResponseDTO>> getQuestionsByBatchAndDepartment(@RequestParam("batch") String batch, @RequestParam("departmentId") String departmentId) {
		
		List<Question> questions = service.findQuestionsByBatchAndDepartment(batch, departmentId);
		
		List<QuestionResponseDTO> response = new ArrayList<>();
		for(Question quest : questions) {
			QuestionResponseDTO question = new QuestionResponseDTO();
			question.setId(quest.getId());
			question.setDepartmentId(quest.getDepartmentId());
			question.setBatch(quest.getBatch());
			question.setQuestion(quest.getQuestion());
			question.setSubject(quest.getSubject());
			question.setOption1(quest.getOption1());
			question.setOption2(quest.getOption2());
			question.setOption3(quest.getOption3());
			question.setOption4(quest.getOption4());
			question.setAnswer(quest.getAnswer());
			response.add(question);
		}
		return new ResponseEntity<>(response, HttpStatus.FOUND);
	}
	
	@GetMapping("question/batch/department/subject/exam")
	public ResponseEntity<List<String>> getQuestionsIdForExam(@RequestParam("batch") String batch, @RequestParam("departmentId") String departmentId, @RequestParam("subject") String subject) {
		
		List<String> ids = service.findQuestionsIdForExam(batch, departmentId, subject);
		return new ResponseEntity<>(ids, HttpStatus.OK);
	}
}
