package com.learning.college.CollegeManagement.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learning.college.CollegeManagement.dto.BatchRequestDTO;
import com.learning.college.CollegeManagement.dto.BatchResponseDTO;
import com.learning.college.CollegeManagement.entity.Batch;
import com.learning.college.CollegeManagement.exception.BatchException;
import com.learning.college.CollegeManagement.service.BatchService;

@RestController
@RequestMapping("college/api/v1")
public class BatchController {

	@Autowired
	private BatchService batchService;
	
	@PostMapping("batch")
	public ResponseEntity<BatchResponseDTO> createBatch(@RequestBody BatchRequestDTO batch) throws BatchException {
		Batch payload = new Batch();
		payload.setAcademicYear(batch.getAcademicYear());
		payload.setStatus(batch.getStatus());
		
		Batch response = batchService.createBatch(payload);
		
		if(response == null)
			throw new BatchException("Batch creation failed", "Batch Couldn't be created right now");
		
		BatchResponseDTO responseDto = new BatchResponseDTO();
		responseDto.setId(response.getId());
		responseDto.setAcademicYear(response.getAcademicYear());
		responseDto.setStatus(response.getStatus());
		
		return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
	}
	
	@GetMapping("batch")
	public ResponseEntity<List<BatchResponseDTO>> findAllBatches() throws BatchException {
		List<Batch> batch = batchService.findAllBatch();
		if(batch == null)
			throw new BatchException("Batch not available", "No Batch created yet");
	
		List<BatchResponseDTO> response = new ArrayList<BatchResponseDTO>();
		for(Batch b : batch) {
			BatchResponseDTO dto = new BatchResponseDTO();
			dto.setId(b.getId());
			dto.setAcademicYear(b.getAcademicYear());
			dto.setStatus(b.getStatus());
			response.add(dto);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("batch/{id}")
	public ResponseEntity<BatchResponseDTO> findById(@PathVariable("id") String id) throws BatchException {
		Batch batch = batchService.findById(id);
		if(batch == null)
			throw new BatchException("Batch not available", "No Batch created yet");
	
		BatchResponseDTO response = new BatchResponseDTO();
		response.setId(batch.getId());
		response.setAcademicYear(batch.getAcademicYear());
		response.setStatus(batch.getStatus());
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("batch/year")
	public ResponseEntity<BatchResponseDTO> findByYear(@RequestParam("year") String year) throws BatchException {
		Batch batch = batchService.findByYear(year);
		if(batch == null)
			throw new BatchException("Batch not available", "No Batch created yet for this year");
	
		BatchResponseDTO response = new BatchResponseDTO();
		response.setId(batch.getId());
		response.setAcademicYear(batch.getAcademicYear());
		response.setStatus(batch.getStatus());
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PutMapping("batch")
	public ResponseEntity<BatchResponseDTO> updateBatch(@RequestBody BatchRequestDTO request) throws BatchException {
		
		Batch availibility = batchService.findByYear(request.getAcademicYear());
		if(availibility == null)
			throw new BatchException("Batch not found", "There is no batch for the year "+request.getAcademicYear() + " to update");
		
		availibility.setStatus(request.getStatus());
		batchService.updateBatch(availibility);
		
		BatchResponseDTO response = new BatchResponseDTO();
		response.setId(availibility.getId());
		response.setAcademicYear(availibility.getAcademicYear());
		response.setStatus(availibility.getStatus());
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
}
