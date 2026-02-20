package com.srots.dto.jobdto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JobHiringStatsDTO {
	private String jobId;
	private String jobTitle;
	private int totalRounds;
	private List<JobRoundProgressDTO> rounds;

}
