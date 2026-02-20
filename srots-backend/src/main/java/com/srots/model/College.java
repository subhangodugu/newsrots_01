package com.srots.model;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "colleges")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class College {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id", length = 36, columnDefinition = "VARCHAR(36)")
	private String id;

	@Column(nullable = false)
	private String name;
	@Column(nullable = false, unique = true)
	private String code;
	private String type;
	private String email;
	private String phone;
	private String landline;
	private String logoUrl;

	@JdbcTypeCode(SqlTypes.JSON)
	@Column(columnDefinition = "json")
	private String addressJson;

	@JdbcTypeCode(SqlTypes.JSON)
	@Column(columnDefinition = "json")
	private String socialMedia;

	@JdbcTypeCode(SqlTypes.JSON)
	@Column(columnDefinition = "json")
	private String aboutSections;

	@JdbcTypeCode(SqlTypes.JSON)
	@Column(columnDefinition = "json")
	private String branches;

	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime createdAt;

	@UpdateTimestamp
	private LocalDateTime updatedAt;

	@OneToMany(mappedBy = "college", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Post> posts;

	@OneToMany(mappedBy = "college", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Job> jobs; // This ensures Jobs are deleted when College is deleted

	// Inside College.java
	@OneToMany(mappedBy = "college")
	@JsonIgnoreProperties({ "college", "studentProfile", "educationRecords", "experiences" })
	private List<User> users;

}