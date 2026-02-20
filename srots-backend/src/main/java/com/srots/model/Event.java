package com.srots.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import java.time.*;

@Entity
@Table(name = "events")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {
	@Id
	private String id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "college_id")
	private College college;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "created_by_user_id")
	private User createdBy;

	private String title;
	@Column(columnDefinition = "TEXT")
	private String description;

	@Enumerated(EnumType.STRING)
	@Column(name = "event_type")
	private EventType eventType;

	public enum EventType {
		Drive, Class, Exam, Holiday, Meeting, Time_Table, Training, Workshop;

		public String getDisplayName() {
			return name().replace("_", " ");
		}
	}

	private LocalDate startDate;
	private LocalDate endDate;
	private LocalTime startTime;
	private LocalTime endTime;

	@JdbcTypeCode(SqlTypes.JSON)
	private String targetBranches;

	@JdbcTypeCode(SqlTypes.JSON)
	private String targetYears; // Added to match DB: target_years

	@JdbcTypeCode(SqlTypes.JSON)
	@Column(name = "schedule_json")
	private String scheduleJson;

	private LocalDateTime createdAt = LocalDateTime.now();

	// Standard getters, setters, and constructors...
}