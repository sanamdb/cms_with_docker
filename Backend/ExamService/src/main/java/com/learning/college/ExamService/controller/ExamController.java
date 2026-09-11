package com.learning.college.ExamService.controller;

import java.net.http.HttpRequest;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.college.ExamService.dto.ExamAnswerSheetDTO;
import com.learning.college.ExamService.dto.ExamPaperResponseDTO;
import com.learning.college.ExamService.dto.ExamRequestDTO;
import com.learning.college.ExamService.dto.ExamResponseDTO;
import com.learning.college.ExamService.dto.ExamResultRequestDTO;
import com.learning.college.ExamService.dto.ExamResultResponseDTO;
import com.learning.college.ExamService.dto.ExamSubmissionResponse;
import com.learning.college.ExamService.dto.StudentExamResponse;
import com.learning.college.ExamService.entity.ExamAnswerSheet;
import com.learning.college.ExamService.entity.ExamResult;
import com.learning.college.ExamService.entity.Examination;
import com.learning.college.ExamService.service.ExaminationService;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("exam/api/v1")
@Slf4j
public class ExamController {
	
	@Autowired
	private ExaminationService examService;
	
	@PostMapping("examination")
	public ResponseEntity<ExamResponseDTO> createExam(@RequestBody ExamRequestDTO request) {
		
		Examination exam = new Examination();
		exam.setExamName(request.getExamName());
		exam.setBatch(request.getBatch());
		exam.setDepartmentId(request.getDepartmentId());
		exam.setDescription(request.getDescription());
		exam.setStartDateTime(request.getStartDateTime());
		exam.setEndDateTime(request.getEndDateTime());
		
		Examination examRes = examService.createExam(exam, request.getSubjects());
		
		ExamResponseDTO response = new ExamResponseDTO();
		response.setId(examRes.getId());
		response.setBatch(examRes.getBatch());
		response.setDepartmentId(examRes.getDepartmentId());
		response.setDescription(examRes.getDescription());
		response.setStartDateTime(examRes.getStartDateTime());
		response.setEndDateTime(examRes.getEndDateTime());
		response.setExamName(examRes.getExamName());
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
		
	}
	
	@GetMapping("examination/{id}")
	@RateLimiter(name = "examRateLimiter", fallbackMethod = "rateLimitFallback")
	@Bulkhead(name = "examBulkhead", fallbackMethod = "examBulkHeadFallback")
	public ResponseEntity<ExamPaperResponseDTO> fetchPaper(@PathVariable("id") String id) {
		ExamPaperResponseDTO responseDTO = examService.fetchExamPaper(id);
		log.info("Get Exam paper from Exam");
		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	}
	
	public ResponseEntity<ExamPaperResponseDTO> rateLimitFallback(String id, Throwable throwable) {
        System.err.println("Rate limit exceeded for exam paper requests!");
        ExamPaperResponseDTO res = new ExamPaperResponseDTO();
        res.setDescription("To many request try after sometime");
        return new ResponseEntity<>(res, HttpStatus.TOO_MANY_REQUESTS);
    }
	
	public ResponseEntity<ExamPaperResponseDTO> examBulkHeadFallback(String id, Throwable throwable) {
		 System.err.println("Bulkhead full! Reason: " + throwable.getMessage());
	        ExamPaperResponseDTO res = new ExamPaperResponseDTO();
	        res.setDescription("Server is under heavy load. Please try again shortly.");
	        return new ResponseEntity<>(res, HttpStatus.BANDWIDTH_LIMIT_EXCEEDED);
	}
	
	@PostMapping("submit")
	public ResponseEntity<ExamSubmissionResponse> submitExam(@RequestBody ExamAnswerSheetDTO requestDto, HttpServletRequest request) {
		
		String userId = request.getHeader("userId");
		ExamAnswerSheet payload = new ExamAnswerSheet();
		
		payload.setExamId(requestDto.getExamId());
		payload.setUsername(userId);
		payload.setAnswerSheet(requestDto.getAnswerSheet());
		payload.setSubmissionDateTime(LocalDateTime.now());
		
		examService.submitExam(payload);
		
		ExamSubmissionResponse response = new ExamSubmissionResponse();
		
		response.setMessage("You have successfully submitted your exam");
		response.setTime(LocalDateTime.now());
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
		
	}
	
	@PostMapping("calculate/result")
	public ResponseEntity<ExamResultResponseDTO> calculateExamResult(@RequestBody ExamResultRequestDTO requestDto) {
		ExamResultResponseDTO response = examService.calculateExamResult(requestDto);
		ExamResult result = new ExamResult();
		result.setUsername(response.getUsername());
		result.setExamId(response.getExamId());
		result.setTotalMarks(response.getTotalMarks());
		result.setObtainedMarks(response.getObtainedMarks());
		examService.saveResult(result);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("student/result")
	public ResponseEntity<ExamResultResponseDTO> getExamResult(@RequestBody ExamResultRequestDTO requestDto) {
		ExamResult result = examService.findResult(requestDto);
		ExamResultResponseDTO response = new ExamResultResponseDTO();
		response.setUsername(result.getUsername());
		response.setExamId(result.getExamId());
		response.setTotalMarks(result.getTotalMarks());
		response.setObtainedMarks(result.getObtainedMarks());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("examination/schedule/{departmentId}/{batch}")
	public ResponseEntity<StudentExamResponse> getScheduleExamDetails(@PathVariable("departmentId") String departmentId, @PathVariable("batch") String batch, HttpServletRequest request) {
		
		String userId = request.getHeader("userId");
		Examination examination = examService.getExamByDepartmentAndBatch(departmentId, batch);
		ExamAnswerSheet submission = null;
		ExamResult result = null;
		
		if(examination != null && examination.getId()!=null) {
			submission = examService.getSubmissionDetails(examination.getId(), userId);
			result = examService.getResult(examination.getId(), userId);
		}
		
		StudentExamResponse response = new StudentExamResponse();
		response.setExam(examination);
		response.setSubmission(submission);
		response.setResult(result);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
