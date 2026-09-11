package com.learning.college.CollegeManagement.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learning.college.CollegeManagement.dto.CampusDepartmentDetailsResponseDTO;
import com.learning.college.CollegeManagement.dto.CampusDepartmentRequestDTO;
import com.learning.college.CollegeManagement.dto.CampusDepartmentResponseDTO;
import com.learning.college.CollegeManagement.entity.Campus;
import com.learning.college.CollegeManagement.entity.CampusDepartment;
import com.learning.college.CollegeManagement.entity.Department;
import com.learning.college.CollegeManagement.exception.CampusNotFoundException;
import com.learning.college.CollegeManagement.exception.DepartmentNotFoundException;
import com.learning.college.CollegeManagement.exception.GenericCollegeException;
import com.learning.college.CollegeManagement.service.CampusDepartmentService;
import com.learning.college.CollegeManagement.service.CampusService;
import com.learning.college.CollegeManagement.service.DepartmentService;

@RestController
@RequestMapping("college/api/v1")
public class CampusDepartmentController {
	
	@Autowired
	private CampusService campusService;
	
	@Autowired
	private DepartmentService deptService;
	
	@Autowired
	private CampusDepartmentService service;
	
	@PostMapping("campus/department")
	public ResponseEntity<CampusDepartmentResponseDTO> startCourseInCampus(@RequestBody CampusDepartmentRequestDTO campDept) throws CampusNotFoundException, DepartmentNotFoundException, GenericCollegeException {
		
		Campus campus = campusService.findCampusById(campDept.getCampusId());
		if(campus == null)
			throw new CampusNotFoundException("Campus not found", "Campus not found with id "+campDept.getCampusId() + " , retry after reverifying");
		
		Department department = deptService.findDepartmentById(campDept.getDepartmentId());
		if(department == null)
			throw new DepartmentNotFoundException("Department not found", "Department not found with id "+campDept.getDepartmentId() + " , retry after reverifying");
		
		
		CampusDepartment payload = new CampusDepartment();
		payload.setCampusId(campDept.getCampusId());
		payload.setDepartmentId(campDept.getDepartmentId());
		payload.setBuildingBlock(campDept.getBuildingBlock());
		payload.setContactEmail(campDept.getContactEmail());
		payload.setFees(campDept.getFees());
		payload.setHeadOfDepartment(campDept.getHeadOfDepartment());
		payload.setIntakeCapacity(campDept.getIntakeCapacity());
		payload.setPhoneNumber(campDept.getPhoneNumber());
		
		CampusDepartment response = service.createCampusDepartment(payload);
		
		if(response == null)
			throw new GenericCollegeException("Campus department link failed", "Campus "+payload.getCampusId() + " couldn't start department "+payload.getDepartmentId() + " , try after sometime.");
		
		CampusDepartmentResponseDTO responseDto = new CampusDepartmentResponseDTO();
		responseDto.setId(response.getId());
		responseDto.setCampusId(response.getCampusId());
		responseDto.setDepartmentId(response.getDepartmentId());
		responseDto.setBuildingBlock(response.getBuildingBlock());
		responseDto.setContactEmail(response.getContactEmail());
		responseDto.setFees(response.getFees());
		responseDto.setHeadOfDepartment(response.getHeadOfDepartment());
		responseDto.setIntakeCapacity(response.getIntakeCapacity());
		responseDto.setPhoneNumber(response.getPhoneNumber());
		responseDto.setStatus(response.getStatus());
		responseDto.setEstablishedDate(response.getEstablishedDate());
		
		return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
		
	}
	
	@GetMapping("campus/department")
	public ResponseEntity<List<CampusDepartmentDetailsResponseDTO>> findCampusDepartmentDetails(@RequestParam("status") String status) throws CampusNotFoundException {
		
		List<CampusDepartmentDetailsResponseDTO> res = service.findCampusDepartmentDetails(status);
		
		if(res == null)
			throw new CampusNotFoundException("We don't have any active course", "We are closed");
		
		if(res.isEmpty())
			throw new CampusNotFoundException("We don't have any active course", "We are closed");
		
		return new ResponseEntity<List<CampusDepartmentDetailsResponseDTO>>(res, HttpStatus.OK);
		
	}
	
	@GetMapping("campus/department/fees")
	public ResponseEntity<List<CampusDepartmentResponseDTO>> getCampusWithLesserFee(@RequestParam("fees") long fees) throws CampusNotFoundException {
		
		List<CampusDepartment> responses = service.findCampusWithLesserFees(fees);
		
		if(responses == null)
			throw new CampusNotFoundException("No campus with this fee", "We don't have any offered campus with your desired fees");
		
		if(responses.isEmpty())
			throw new CampusNotFoundException("No campus with this fee", "We don't have any offered campus with your desired fees");
		
		List<CampusDepartmentResponseDTO> resList = new ArrayList<>();
		
		for(CampusDepartment cd : responses) {
			CampusDepartmentResponseDTO dto = new CampusDepartmentResponseDTO();
			dto.setId(cd.getId());
			dto.setCampusId(cd.getCampusId());
			dto.setDepartmentId(cd.getDepartmentId());
			dto.setContactEmail(cd.getContactEmail());
			dto.setEstablishedDate(cd.getEstablishedDate());
			dto.setBuildingBlock(cd.getBuildingBlock());
			dto.setFees(cd.getFees());
			dto.setHeadOfDepartment(cd.getHeadOfDepartment());
			dto.setIntakeCapacity(cd.getIntakeCapacity());
			dto.setPhoneNumber(cd.getPhoneNumber());
			dto.setStatus(cd.getStatus());
			resList.add(dto);
		}
		
		return new ResponseEntity<>(resList, HttpStatus.OK);
		
	}

}
