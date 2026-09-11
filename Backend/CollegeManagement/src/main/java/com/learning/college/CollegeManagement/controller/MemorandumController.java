package com.learning.college.CollegeManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.college.CollegeManagement.entity.Memorandum;
import com.learning.college.CollegeManagement.service.MemorandumService;

@RestController
@RequestMapping("college/api/v1")
public class MemorandumController {
	
	@Autowired
	private MemorandumService memoService;
	
	@PostMapping("event")
	public ResponseEntity<Memorandum> postMemorandum(@RequestBody Memorandum payload) {
		Memorandum res = memoService.post(payload);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@GetMapping("event")
	public ResponseEntity<List<Memorandum>> getMemorandum(){
		List<Memorandum> responses = memoService.get();
		return new ResponseEntity<>(responses, HttpStatus.OK);
	}

}
