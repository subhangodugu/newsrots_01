package com.srots.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UserCreateRequest {
	private String username;
	private String name;
	private String email;
	private String phone;
	private String department;
	@JsonProperty("aadhaarNumber")
	private String aadhaar;

	// New Fields for CP and Srots Users
	private String alternativeEmail;
	private String alternativePhone;
	private String bio;
	private String experience; // e.g., "12+ Years"
	private String education; // e.g., "M.Tech, MBA"
	private String avatarUrl; // To store the link to the profile photo

	private AddressRequest address;

	// College/Role context
	private String collegeId;
	private Boolean isCollegeHead;

	// Student specific data
	private StudentProfileRequest studentProfile;

}
