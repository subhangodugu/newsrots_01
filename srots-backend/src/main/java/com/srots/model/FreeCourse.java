package com.srots.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "free_courses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FreeCourse {
	@Id
	// Removed @GeneratedValue because we will set it manually in Service
	@Column(name = "id", length = 36, columnDefinition = "VARCHAR(36)")
	private String id;

	@Column(nullable = false)
	private String name;

	private String technology;

	@Column(columnDefinition = "TEXT")
	private String description;

	public enum CoursePlatform {
		YOUTUBE, UDEMY, COURSERA, LINKEDIN, OTHER
	}

	@Column(nullable = false, unique = true, length = 512)
	private String link;

	@Enumerated(EnumType.STRING)
	private CoursePlatform platform;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "posted_by_id")
	private User postedBy;

	private String postedByName;

	public enum CourseStatus {
		ACTIVE, INACTIVE, PENDING
	}

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private CourseStatus status = CourseStatus.ACTIVE;

	private LocalDateTime lastVerifiedAt;

	@CreationTimestamp
	private LocalDateTime createdAt;

}