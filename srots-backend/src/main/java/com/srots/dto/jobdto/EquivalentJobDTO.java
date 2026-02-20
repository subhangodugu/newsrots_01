package com.srots.dto.jobdto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EquivalentJobDTO {
	private String id;
	private String title;
	private String companyName;
	private String hiringDepartment;
	private String jobType;
	private String workMode;
	private String location;
	private String salaryRange;
	private String summary;

	// These must be Strings to hold the JSON text from the Entity
	private String responsibilitiesJson;
	private String qualificationsJson;
	private String preferredQualificationsJson;
	private String benefitsJson;

	private String companyCulture;
	private String physicalDemands;
	private String eeoStatement;
	private String internalId;
	private LocalDate applicationDeadline;
	private String externalLink;
	private String status;
	private LocalDateTime postedAt; // Matches Entity LocalDateTime

	// Eligibility Criteria
	private BigDecimal minUgScore;
	private Integer maxBacklogs;
	private BigDecimal min10thScore;
	private BigDecimal min12thScore;
	private Boolean allowGaps;

	// Updated to String to match your Entity's @JdbcTypeCode(SqlTypes.JSON) String
	// fields
	private String allowedBranches;
	private String eligibleBatches;
	private String roundsJson;
	private String requiredFieldsJson;
	private String attachmentsJson;

	// Flat DTOs to stop recursion
	private CollegeSummaryDTO college;
	private UserSummaryDTO postedBy;

}