package com.srots.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "audit_logs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuditLog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String action; // e.g., "SOFT_DELETE", "HARD_DELETE", "STATUS_CHANGE"
	private String targetId; // The ID of the Course
	private String targetName; // The Name of the Course (useful if course is hard deleted)
	private String performedBy; // Admin Email or Username

	@Column(columnDefinition = "TEXT")
	private String details; // Any extra info (e.g., "Changed status from PENDING to ACTIVE")

	@CreationTimestamp
	private LocalDateTime timestamp;

}