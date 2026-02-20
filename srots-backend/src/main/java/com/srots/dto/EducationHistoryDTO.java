package com.srots.dto;

import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EducationHistoryDTO {
	private String level; // e.g., "Class 10" or "Undergraduate"
	private String institution;
	private String board;
	private String yearOfPassing;
	private String score;
	private String scoreType; // e.g., "CGPA", "Percentage"

	private Integer currentArrears;

	private String specialization; // Added to match entity
	private List<Map<String, Object>> semesters;

	public EducationHistoryDTO(String level, String institution, String board, String yearOfPassing, String score,
			String scoreType) {
		this.level = level;
		this.institution = institution;
		this.board = board;
		this.yearOfPassing = yearOfPassing;
		this.score = score;
		this.scoreType = scoreType;
		this.currentArrears = 0; // Default value
	}

}