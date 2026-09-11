package com.learning.college.ExamService.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Examination {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
	private String id;
	private String examName;
	private String departmentId;
	private String batch;
	private LocalDateTime startDateTime;
	private LocalDateTime endDateTime;
	private String description;
	@ElementCollection
	private List<String> questionId;

}


/*
{
"examName": "Mid-Term Thermodynamics Assessment",
"departmentId": "725dfdca-0629-453f-8adf-0dcab6a5ab55",
"batch": "2026",
"startDateTime": "2026-08-10T09:30:00",
"endDateTime": "2026-08-10T11:30:00",
"description": "Mid-term examination covering Thermodynamics Modules 1 to 3.",
"questionIds": [
  "b8e8f810-741a-4c2a-97a1-8930a2180801",
  "f1a942cd-2921-4f11-b0e2-7629bc122902"
]
}
*/