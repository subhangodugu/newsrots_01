package com.srots.dto.jobdto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JobRoundProgressDTO {
	private int roundNumber;
	private String roundName;
	private long passedCount;
	private long rejectedCount;
	private long pendingCount; // Still in this round, not yet processed
	private String status; // e.g., "Completed", "In Progress", "Upcoming"

}
