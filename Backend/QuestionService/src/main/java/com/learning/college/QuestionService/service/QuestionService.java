package com.learning.college.QuestionService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.college.QuestionService.entity.Question;
import com.learning.college.QuestionService.repository.QuestionRepository;

@Service
public class QuestionService {

	@Autowired
	private QuestionRepository repo;

	public Question createQuestion(Question questionPayload) {
		return repo.save(questionPayload);
	}

	public List<Question> findAllQuestions() {
		return repo.findAll();
	}

	public Question updateQuestion(Question questionPayload) {
		return repo.save(questionPayload);
	}

	public Question findQuestionById(String id) {
		return repo.findById(id).orElse(new Question());
	}

	public Question deleteQuestionById(String id) {
		Question question = repo.findById(id).orElse(new Question());
		if(question.getId() != null)
			repo.deleteById(id);
		return question;
	}

	public List<Question> findQuestionsByBatchAndDepartmentAndSubject(String batch, String departmentId,
			String subject) {
		return repo.findByBatchAndDepartmentIdAndSubject(batch, departmentId, subject);
	}

	public List<Question> findQuestionsByBatchAndDepartment(String batch, String departmentId) {
		return repo.findByBatchAndDepartmentId(batch, departmentId);
	}

	public List<String> findQuestionsIdForExam(String batch, String departmentId, String subject) {
		return repo.getTheQuestionForExam(batch, departmentId, subject);
	}
}
