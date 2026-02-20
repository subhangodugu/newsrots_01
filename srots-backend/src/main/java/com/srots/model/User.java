package com.srots.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
	@Id
	@Column(length = 100)
	private String id;

	@Column(unique = true, nullable = false)
	private String username;

	@Column(nullable = false, unique = true)
	private String email;

	@JsonIgnore // CRITICAL: Keep this for security
	@Column(name = "password_hash", nullable = false)
	private String passwordHash;

	@Column(nullable = false)
	private String fullName;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Role role;

	public enum Role {
		ADMIN, SROTS_DEV, CPH, STAFF, STUDENT
	}

	@JsonProperty("collegeId") // This adds a new field "collegeId" to your JSON
	public String getCollegeIdOnly() {
		return (college != null) ? college.getId() : null;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "college_id")
	@JsonIgnoreProperties({
			"hibernateLazyInitializer",
			"handler",
			"users", // CRITICAL: Stop the college from listing all its users again
			"jobs", // Stop the college from listing all its jobs again
			"posts"
	})
	private College college;

	private Boolean isRestricted = false;
	private Boolean isCollegeHead = false;

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	// @JsonManagedReference
	@JsonIgnore
	private StudentProfile studentProfile;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_user_id")
	@JsonIgnore
	private User parentUser;

	private String experience;
	private String education;

	private String avatarUrl;
	private String phone;
	private String alternativeEmail;
	private String alternativePhone;
	private String aadhaarNumber;

	@Column(columnDefinition = "TEXT")
	private String bio;

	private String department;

	@JdbcTypeCode(SqlTypes.JSON)
	private String addressJson;

	// used for the change password when user forget passsword
	private String resetToken;
	private LocalDateTime tokenExpiry;

	// Add this to User.java
	private String lastDeviceInfo;

	@CreationTimestamp
	private LocalDateTime createdAt;

	@UpdateTimestamp
	private LocalDateTime updatedAt;

	// ... inside User class ...

	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true) // Add orphanRemoval
	@JsonIgnoreProperties("student")
	private List<EducationRecord> educationRecords = new ArrayList<>();

	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties("student")
	private List<StudentExperience> experiences = new ArrayList<>();

	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties("student")
	private List<StudentProject> projects = new ArrayList<>();

	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties("student")
	private List<StudentCertification> certifications = new ArrayList<>();

	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties("student")
	private List<StudentLanguage> languages = new ArrayList<>();

	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties("student")
	private List<StudentSocialLink> socialLinks = new ArrayList<>();

	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties("student")
	private List<StudentResume> resumes = new ArrayList<>();

	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties("student")
	private List<StudentSkill> skills = new ArrayList<>();

}