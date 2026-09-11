package com.learning.college.CollegeManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.learning.college.CollegeManagement.dto.CampusDepartmentDetailsResponseDTO;
import com.learning.college.CollegeManagement.entity.Campus;
import com.learning.college.CollegeManagement.entity.CampusDepartment;

@Repository
public interface CampusDepartmentRepo extends JpaRepository<CampusDepartment, String>{
	

	@Query("select new com.learning.college.CollegeManagement.dto.CampusDepartmentDetailsResponseDTO(cd.campusId, cd.departmentId, cd.headOfDepartment, cd.fees, cd.buildingBlock, cd.contactEmail, cd.phoneNumber, cd.intakeCapacity, cd.establishedDate, c.name, c.city, c.address, d.code, d.name, d.description, d.duration, d.termType) from CampusDepartment cd, Campus c, Department d where cd.status=?1 and cd.campusId=c.id and cd.departmentId=d.id")
	public List<CampusDepartmentDetailsResponseDTO> getCampusDepartmentDetails(String status);
	
	@Query("select cd from CampusDepartment cd where fees<= :fees")
	public List<CampusDepartment> getLesserFeesCampus(@Param("fees") long fees);

	public List<CampusDepartment> findByCampusId(String campusId);
}



