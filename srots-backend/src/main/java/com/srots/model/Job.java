package com.srots.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonValue;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "jobs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Job {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(columnDefinition = "CHAR(36)")
	private String id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "college_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "jobs", "posts", "users" })
	private College college;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "posted_by_id")
	@JsonIgnoreProperties({
			"hibernateLazyInitializer",
			"handler",
			"passwordHash",
			"jobs", // Stop loop back to jobs
			"college", // Stop loop back to college (since Job already has college)
			"studentProfile",
			"educationRecords"
	})
	private User postedBy;

	private String title;
	private String companyName;
	private String hiringDepartment;

	@Enumerated(EnumType.STRING)
	private JobType jobType;

	@Enumerated(EnumType.STRING)
	private WorkMode workMode;

	private String location;
	private String salaryRange;

	@Column(columnDefinition = "TEXT")
	private String summary;

	@JdbcTypeCode(SqlTypes.JSON)
	private String responsibilitiesJson;

	@JdbcTypeCode(SqlTypes.JSON)
	private String qualificationsJson;

	@JdbcTypeCode(SqlTypes.JSON)
	private String preferredQualificationsJson;

	@JdbcTypeCode(SqlTypes.JSON)
	private String benefitsJson;

	@Column(columnDefinition = "TEXT")
	private String companyCulture;

	@Column(columnDefinition = "TEXT")
	private String physicalDemands;

	@Column(columnDefinition = "TEXT")
	private String eeoStatement;

	private String internalId;
	private LocalDate applicationDeadline;

	@Column(columnDefinition = "TEXT")
	private String externalLink;

	@Builder.Default
	private JobStatus status = JobStatus.Active;

	@CreationTimestamp
	private LocalDateTime postedAt;

	@Column(precision = 5, scale = 2)
	private BigDecimal minUgScore;
	private String formatUg;
	private Integer maxBacklogs;

	@Column(precision = 5, scale = 2)
	private BigDecimal min10thScore;
	private String format10th;

	@Column(precision = 5, scale = 2)
	private BigDecimal min12thScore;
	private String format12th;

	@Builder.Default
	private Boolean isDiplomaEligible = false;

	@Column(precision = 5, scale = 2)
	private BigDecimal minDiplomaScore;
	private String formatDiploma;

	@Builder.Default
	private Boolean allowGaps = false;
	@Builder.Default
	private Integer maxGapYears = 0;

	@JdbcTypeCode(SqlTypes.JSON)
	private String allowedBranches;

	@JdbcTypeCode(SqlTypes.JSON)
	private String eligibleBatches;

	@JdbcTypeCode(SqlTypes.JSON)
	private String roundsJson;

	@JdbcTypeCode(SqlTypes.JSON)
	private String requiredFieldsJson;

	@JdbcTypeCode(SqlTypes.JSON)
	private String attachmentsJson;

	private String avoidListUrl;

	@OneToMany(mappedBy = "job", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<Application> applications;

	// --- ENUMS ---

	public enum JobStatus {
		Active, Closed, Draft
	}

	public enum JobType {
		Full_Time("Full Time"), Internship("Internship"), Contract("Contract");

		private final String display;

		JobType(String display) {
			this.display = display;
		}

		@JsonValue
		public String getDisplay() {
			return display;
		}

		public static JobType fromString(String text) {
			if (text == null || text.isBlank())
				return null;
			String clean = text.trim().replace(" ", "").replace("_", "").toLowerCase();
			for (JobType t : JobType.values()) {
				if (t.name().replace("_", "").toLowerCase().equals(clean))
					return t;
			}
			return null;
		}
	}

	public enum WorkMode {
		On_Site("On-Site"), Remote("Remote"), Hybrid("Hybrid");

		private final String display;

		WorkMode(String display) {
			this.display = display;
		}

		@JsonValue
		public String getDisplay() {
			return display;
		}

		public static WorkMode fromString(String text) {
			if (text == null || text.isBlank())
				return null;
			String clean = text.trim().replace(" ", "").replace("-", "").replace("_", "").toLowerCase();
			for (WorkMode m : WorkMode.values()) {
				if (m.name().replace("_", "").toLowerCase().equals(clean))
					return m;
			}
			return null;
		}
	}

}