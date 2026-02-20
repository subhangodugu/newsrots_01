package com.srots.dto;

import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentProfileRequest {
	// Basic Academic
	private String rollNumber;
	private String branch;
	private Integer batch;
	private String course;
	private String gender;

	// Administrative Fields
	private String mentor;
	private String advisor;
	private String coordinator;
	private String placementCycle;

	// Personal / Contact
	private String dob; // String to be parsed as LocalDate
	private String nationality;
	private String religion;
	private String personalEmail;
	private String instituteEmail;
	private String whatsappNumber;

	// Parents
	private String fatherName;
	private String motherName;
	private String fatherOccupation;
	private String motherOccupation;
	private String parentPhone;
	private String parentEmail;

	// Address (Objects will be converted to JSON strings)
	private Object currentAddress;
	private Object permanentAddress;

	// Education History (List for the loop)
	private List<EducationHistoryDTO> educationHistory;

}