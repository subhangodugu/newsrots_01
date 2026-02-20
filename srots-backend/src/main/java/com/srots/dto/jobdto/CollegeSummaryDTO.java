package com.srots.dto.jobdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CollegeSummaryDTO {
	private String id;
	private String name;
	private String code;
	private String type;
	private String logoUrl;

}