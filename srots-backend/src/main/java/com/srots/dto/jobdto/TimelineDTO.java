package com.srots.dto.jobdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimelineDTO {
	private String roundName;
	private String status; // Qualified, Not Selected, Pending, Wait for Update
	private String date;

}