package com.learning.college.CollegeManagement.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learning.college.CollegeManagement.dto.CampusDepartmentFullResponseDTO;
import com.learning.college.CollegeManagement.dto.CampusDepartmentResponseDTO;
import com.learning.college.CollegeManagement.dto.CampusRequestDTO;
import com.learning.college.CollegeManagement.dto.CampusResponseDTO;
import com.learning.college.CollegeManagement.dto.DepartmentForCampus;
import com.learning.college.CollegeManagement.dto.DepartmentResponseDTO;
import com.learning.college.CollegeManagement.entity.Campus;
import com.learning.college.CollegeManagement.entity.CampusDepartment;
import com.learning.college.CollegeManagement.entity.Department;
import com.learning.college.CollegeManagement.exception.CampusNotFoundException;
import com.learning.college.CollegeManagement.exception.GenericCollegeException;
import com.learning.college.CollegeManagement.service.CampusDepartmentService;
import com.learning.college.CollegeManagement.service.CampusService;
import com.learning.college.CollegeManagement.service.DepartmentService;


@RestController
@RequestMapping("college/api/v1")
public class CampusController {
	
	@Autowired
	private CampusService service;
	
	@Autowired
	private CampusDepartmentService campDeptService;
	
	@Autowired
	private DepartmentService deptService;
	
	@PostMapping("campus")
	//@PostMapping(path = "campus", consumes = {"application/json"}, produces = {"application/xml"})
	public ResponseEntity<CampusResponseDTO> createCampus(@RequestBody CampusRequestDTO campus) throws GenericCollegeException {
		
		Campus campusReq = new Campus();
		campusReq.setName(campus.getName());
		campusReq.setAddress(campus.getAddress());
		campusReq.setCity(campus.getCity());
		
		Campus response = service.saveCampus(campusReq);
		
		if(response == null)
			throw new GenericCollegeException("Campus creation failed", "Campus couldn't be created, try after sometime");
		
		CampusResponseDTO res = new CampusResponseDTO();
		res.setId(response.getId());
		res.setName(response.getName());
		res.setCity(response.getCity());
		res.setAddress(response.getAddress());
		res.setEstablishedDate(response.getEstablishedDate());
		
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}
	
	@GetMapping("campus")
	public ResponseEntity<Page<CampusResponseDTO>> fetchCampus(@RequestParam("page") int page, @RequestParam("size") int size) throws CampusNotFoundException {
		
		Page<Campus> campuses = service.findAllCampus(page, size);
		
		if(campuses.getTotalElements() == 0)
			throw new CampusNotFoundException("Campus not opened yet", "There is no campus opened yet");
		
		Page<CampusResponseDTO> response = campuses.map(campus -> {
			CampusResponseDTO res = new CampusResponseDTO();
			res.setId(campus.getId());
			res.setName(campus.getName());
			res.setAddress(campus.getAddress());
			res.setCity(campus.getCity());
			res.setEstablishedDate(campus.getEstablishedDate());
			res.setCampusPicture(campus.getCampusPicture());
			return res;
		});
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("campus/{id}")
	public ResponseEntity<CampusResponseDTO> fetchCampusById(@PathVariable("id") String id) throws CampusNotFoundException {
		
		Campus campus = service.findCampusById(id);
		
		if(campus == null) 
			throw new CampusNotFoundException("Campus not opened yet", "There is no campus opened yet with id "+ id);
		
		CampusResponseDTO response = new CampusResponseDTO();
		response.setId(campus.getId());
		response.setName(campus.getName());
		response.setAddress(campus.getAddress());
		response.setCity(campus.getCity());
		response.setEstablishedDate(campus.getEstablishedDate());
		response.setCampusPicture(campus.getCampusPicture());
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PutMapping("campus/{id}")
	public ResponseEntity<CampusResponseDTO> updateCampus(@RequestBody CampusRequestDTO campus, @PathVariable("id") String id) throws CampusNotFoundException, GenericCollegeException {
		
		Campus campusRes = service.findCampusById(id);
		if(campusRes == null)
			throw new CampusNotFoundException("Campus not opened yet", "There is no campus with id "+ id + " to update");
		
		Campus responseEntity = service.updateCampusById(campus,campusRes, id);
		
		if(responseEntity == null) 
			throw new GenericCollegeException("Campus can not be updated", "Campus can not be updated, try after sometime");
		
		CampusResponseDTO response = new CampusResponseDTO();
		response.setId(responseEntity.getId());
		response.setName(responseEntity.getName());
		response.setAddress(responseEntity.getAddress());
		response.setCity(responseEntity.getCity());
		response.setEstablishedDate(responseEntity.getEstablishedDate());
		response.setCampusPicture(responseEntity.getCampusPicture());
		
		return new ResponseEntity<>(response, HttpStatus.OK);
		
	}
	
	@GetMapping("campus/city")
	public ResponseEntity<List<CampusResponseDTO>> findCampusByCity(@RequestParam("city") String city) throws CampusNotFoundException {
		
		List<Campus> campuses = service.findCampusByCity(city);
		
		if(campuses.isEmpty()) 
			throw new CampusNotFoundException("No campus in city", "There is no campus in your "+ city + " City");
		
		List<CampusResponseDTO> responses = new ArrayList<>();
		
		for(Campus campus : campuses) {
			CampusResponseDTO response = new CampusResponseDTO();
			response.setId(campus.getId());
			response.setName(campus.getName());
			response.setAddress(campus.getAddress());
			response.setCity(campus.getCity());
			response.setEstablishedDate(campus.getEstablishedDate());
			response.setCampusPicture(campus.getCampusPicture());
			
			responses.add(response);
		}
		
		return new ResponseEntity<>(responses, HttpStatus.OK);
	}
	
	
	@DeleteMapping("campus/{id}")
	public ResponseEntity<CampusResponseDTO> deleteCampus(@PathVariable("id") String id) throws CampusNotFoundException, GenericCollegeException {
		
		Campus campus = service.findCampusById(id);
		
		if(campus == null) 
			throw new CampusNotFoundException("No campus to delete", "There is no campus with id "+ id + " to delete");
		
		CampusResponseDTO response = new CampusResponseDTO();
		
		try {
			service.deleteCampusById(id);
			response.setId(campus.getId());
			response.setName(campus.getName());
			response.setAddress(campus.getAddress());
			response.setCity(campus.getCity());
			response.setEstablishedDate(campus.getEstablishedDate());
		} catch (Exception e) {
			throw new GenericCollegeException("Something wrong while deleting", "Something went wrong while deleting campus with id "+ id);
		}
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("campus/offer/department/{campusId}")
	public ResponseEntity<CampusDepartmentFullResponseDTO> getAllOfferDepartmentByCampus(@PathVariable("campusId") String campusId) {
		
		/* Campus details */
		Campus campus = service.findCampusById(campusId);
		CampusResponseDTO campuseDTO = new CampusResponseDTO();
		campuseDTO.setId(campus.getId());
		campuseDTO.setName(campus.getName());
		campuseDTO.setAddress(campus.getAddress());
		campuseDTO.setCity(campus.getCity());
		campuseDTO.setEstablishedDate(campus.getEstablishedDate());
		campuseDTO.setCampusPicture(campus.getCampusPicture());
		 
		/* Campus's Department for a selected campus */
		List<CampusDepartment> campDept = campDeptService.getDepartmentOfferByCampus(campusId);
		List<DepartmentForCampus> listOfCampusDept = new ArrayList<>();
		for(CampusDepartment cd : campDept) {
			
			/* Campus X Department linked details */
			CampusDepartmentResponseDTO campDeptDto = new CampusDepartmentResponseDTO();
			campDeptDto.setId(cd.getId());
			campDeptDto.setCampusId(cd.getCampusId());
			campDeptDto.setDepartmentId(cd.getDepartmentId());
			campDeptDto.setBuildingBlock(cd.getBuildingBlock());
			campDeptDto.setContactEmail(cd.getContactEmail());
			campDeptDto.setFees(cd.getFees());
			campDeptDto.setHeadOfDepartment(cd.getHeadOfDepartment());
			campDeptDto.setIntakeCapacity(cd.getIntakeCapacity());
			campDeptDto.setPhoneNumber(cd.getPhoneNumber());
			campDeptDto.setStatus(cd.getStatus());
			campDeptDto.setEstablishedDate(cd.getEstablishedDate());
			
			/* Department details */
			Department dept = deptService.findDepartmentById(cd.getDepartmentId());
			DepartmentResponseDTO deptDto = new DepartmentResponseDTO();
			deptDto.setId(dept.getId());
			deptDto.setCode(dept.getCode());
			deptDto.setDescription(dept.getDescription());
			deptDto.setDuration(dept.getDuration());
			deptDto.setName(dept.getName());
			deptDto.setTermType(dept.getTermType());
			
			/* Department and campus final response */
			DepartmentForCampus departmentForCampus = new DepartmentForCampus();
			departmentForCampus.setOfferDepartment(campDeptDto);
			departmentForCampus.setDepartment(deptDto);
			
			/* one campus can have multiple department hence a list */
			listOfCampusDept.add(departmentForCampus);
		}
			
		CampusDepartmentFullResponseDTO response = new CampusDepartmentFullResponseDTO();
		response.setCampus(campuseDTO);
		response.setCampusDepartment(listOfCampusDept);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
 
}
