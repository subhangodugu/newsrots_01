package com.srots.model;

import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "applications")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Application {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(columnDefinition = "CHAR(36)")
	private String id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "job_id", nullable = false)
	private Job job;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id", nullable = false)
	private User student;

	@Builder.Default
	private AppStatus status = AppStatus.Applied;

	public enum AppStatus {
		Applied, Shortlisted, Rejected, Hired, Not_Interested, Offer_Released, PLACED
	}

	private String currentRoundStatus;

	private Integer currentRound;

	@CreationTimestamp
	private LocalDateTime appliedAt;

	private LocalDateTime placedAt;

	@Builder.Default
	private AppliedBy appliedBy = AppliedBy.Self; // Default

	public enum AppliedBy {
		Self, CP_Admin, CP_Staff
	}

}