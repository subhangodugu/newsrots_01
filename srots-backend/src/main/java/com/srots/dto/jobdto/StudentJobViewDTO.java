package com.srots.dto.jobdto;

import com.srots.model.Job;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentJobViewDTO {
	// private JobResponseDTO job;
	private Job job;
	private boolean eligible;
	private String NotEligibilityReason;
	private boolean applied;
	private boolean expired;

}