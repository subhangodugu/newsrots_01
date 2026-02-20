package com.srots.dto.jobdto;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JobApplicantsDashboardDTO {
	private String jobTitle;
	private long totalApplicants;
	private Map<String, Long> stats; // Counts of Hired, Rejected, etc.
	private List<Map<String, Object>> roundSummary;
	private List<String> headers; // The column names (Full Name, CGPA, etc.)
	private List<Map<String, Object>> students; // The actual row data

}