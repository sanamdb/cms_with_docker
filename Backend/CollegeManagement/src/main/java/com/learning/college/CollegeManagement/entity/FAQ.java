package com.learning.college.CollegeManagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class FAQ {
	
	@Id
	private int id;
	private String question;
	private String answer;
	private String dept;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
}
