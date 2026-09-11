package com.learning.college.ExamService.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.college.ExamService.dto.AnswerSheet;
import com.learning.college.ExamService.dto.ExamPaperResponseDTO;
import com.learning.college.ExamService.dto.ExamResultRequestDTO;
import com.learning.college.ExamService.dto.ExamResultResponseDTO;
import com.learning.college.ExamService.dto.QuestionPaperResponseDTO;
import com.learning.college.ExamService.dto.QuestionResponseDTO;
import com.learning.college.ExamService.entity.ExamAnswerSheet;
import com.learning.college.ExamService.entity.ExamResult;
import com.learning.college.ExamService.entity.Examination;
import com.learning.college.ExamService.feign.QuestionFeignClient;
import com.learning.college.ExamService.repository.ExamAnswerSheetRepo;
import com.learning.college.ExamService.repository.ExamResultRepository;
import com.learning.college.ExamService.repository.ExaminationRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class ExaminationService {
	
	@Autowired
	private ExaminationRepository repo;
	
	@Autowired
	private ExamAnswerSheetRepo submitRepo;
	
	@Autowired
	private ExamResultRepository resultRepo;
	
	@Autowired
	private QuestionFeignClient feign;

	public Examination createExam(Examination exam, List<String> subjects) {
		if (exam.getQuestionId() == null) {
	        exam.setQuestionId(new ArrayList<>());
	    }
		for(String sub : subjects) {
			List<String> res = feign.getQuestionsIdForExam(exam.getBatch(), exam.getDepartmentId(), sub).getBody();
			exam.getQuestionId().addAll(res);
		}
		return repo.save(exam);
	}

	public ExamPaperResponseDTO fetchExamPaper(String id) {
		Examination exam = repo.findById(id).orElse(new Examination());
		ExamPaperResponseDTO response = new ExamPaperResponseDTO();
		response.setId(exam.getId());
		response.setBatch(exam.getBatch());
		response.setDepartmentId(exam.getDepartmentId());
		response.setDescription(exam.getDescription());
		response.setEndDateTime(exam.getEndDateTime());
		response.setExamName(exam.getExamName());
		response.setStartDateTime(exam.getStartDateTime());
		
		List<QuestionPaperResponseDTO> questions = new ArrayList<>();
		
		for(String questionId : exam.getQuestionId()) {
			QuestionResponseDTO question = feign.getQuestionById(questionId).getBody();
			QuestionPaperResponseDTO result = new QuestionPaperResponseDTO();
			
			result.setId(question.getId());
			result.setDepartmentId(question.getDepartmentId());
			result.setBatch(question.getBatch());
			result.setSubject(question.getSubject());
			result.setQuestion(question.getQuestion());
			result.setOption1(question.getOption1());
			result.setOption2(question.getOption2());
			result.setOption3(question.getOption3());
			result.setOption4(question.getOption4());
			
			questions.add(result);
		}
		
		Map<String, List<QuestionPaperResponseDTO>> questionList = questions.stream().collect(Collectors.groupingBy(q -> q.getSubject()));
		
		response.setQuestions(questionList);
		
		return response;
	}

	public void submitExam(ExamAnswerSheet payload) {
		submitRepo.save(payload);
	}

	@CircuitBreaker(name = "questionServiceCB", fallbackMethod = "getQuestionsFallback")
	public ExamResultResponseDTO calculateExamResult(ExamResultRequestDTO requestDto) {
		ExamAnswerSheet examSheet = submitRepo.findByUsernameAndExamId(requestDto.getUsername(), requestDto.getExamId());
		ExamResultResponseDTO response = new ExamResultResponseDTO();
		response.setUsername(examSheet.getUsername());
		response.setExamId(examSheet.getExamId());
		int totalMark = examSheet.getAnswerSheet().size();
		int obtainedMark = 0;
		for(AnswerSheet ans : examSheet.getAnswerSheet()) {
			QuestionResponseDTO quest = feign.getQuestionById(ans.getQuestionId()).getBody();
			if(ans.getSelectedAnswer().equalsIgnoreCase(quest.getAnswer()))
				obtainedMark = obtainedMark + 1;
		}
		response.setTotalMarks(totalMark);
		response.setObtainedMarks(obtainedMark);
		return response;
	}
	
	//fallback
	public ExamResultResponseDTO getQuestionsFallback(ExamResultRequestDTO requestDto, Throwable throwable) {
	    System.out.println("Question-Service is DOWN! Reason: " + throwable.getMessage());
	    
	    // Create a safe default response so the user gets a clean answer instead of 500 internal server error
	    ExamResultResponseDTO fallbackResponse = new ExamResultResponseDTO();
	    fallbackResponse.setUsername(requestDto.getUsername());
	    fallbackResponse.setExamId(requestDto.getExamId());
	    fallbackResponse.setTotalMarks(0);
	    fallbackResponse.setObtainedMarks(0);
	    
	    return fallbackResponse;
	}

	public void saveResult(ExamResult result) {
		resultRepo.save(result);
	}

	public ExamResult findResult(ExamResultRequestDTO requestDto) {
		return resultRepo.findByUsernameAndExamId(requestDto.getUsername(), requestDto.getExamId());
	}

	public Examination getExamByDepartmentAndBatch(String departmentId, String batch) {
		return repo.findByDepartmentIdAndBatch(departmentId, batch);
	}

	public ExamAnswerSheet getSubmissionDetails(String examId, String userId) {
		return submitRepo.findByUsernameAndExamId(userId, examId);
	}

	public ExamResult getResult(String examId, String userId) {
		return resultRepo.findByUsernameAndExamId(userId, examId);
	}

	

}
