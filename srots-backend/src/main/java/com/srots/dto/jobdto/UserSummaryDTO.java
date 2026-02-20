package com.srots.dto.jobdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSummaryDTO {
	private String id;
	private String fullName;
	private String email;
	private String username;
	private String role;
	private String avatarUrl;

}