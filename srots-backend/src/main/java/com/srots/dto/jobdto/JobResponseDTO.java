
package com.srots.dto.jobdto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * FIXED: JobResponseDTO
 *
 * All field names now match the types.ts Job interface EXACTLY so the frontend
 * can use them without any renaming in the service layer.
 *
 * Key fixes:
 * - company → companyName (matched types.ts)
 * - responsibilities/qualifications/etc → responsibilitiesJson /
 * qualificationsJson / etc (matched types.ts array fields)
 * - requiredFields → requiredStudentFields (matched types.ts)
 * - attachments → documents (matched types.ts)
 * - applicantCount → kept, plus applicants[] array added
 * - Added: formatUg, format10th, format12th, physicalDemands, eeoStatement
 * - allowedBranches, eligibleBatches → now List<String> so frontend can use
 * directly
 */
@Data
public class JobResponseDTO {

	// ── Core identity ──────────────────────────────────────────────────────────
	private String id;
	private String collegeId;

	// ── Basic info (names match types.ts Job exactly) ────────────────────────
	private String title;
	private String companyName; // was "company" – now matches types.ts
	private String hiringDepartment;
	private String jobType; // display value: "Full Time", "Internship" ...
	private String workMode; // display value: "Remote", "On-Site" ...
	private String location;
	private String salaryRange;
	private String summary;
	private String internalId;
	private String externalLink;
	private String companyCulture;
	private String physicalDemands; // was missing
	private String eeoStatement; // was missing
	private String status; // "Active" | "Closed" | "Draft"

	// ── Dates ──────────────────────────────────────────────────────────────────
	private LocalDate applicationDeadline;
	private LocalDate postedAt;

	// ── Ownership ─────────────────────────────────────────────────────────────
	private String postedBy; // full name string
	private String postedById;
	private boolean canEdit;
	private String avoidListUrl;

	// ── Applicant info ────────────────────────────────────────────────────────
	private Long applicantCount; // fast count for badges
	// applicants[] (array of IDs) not sent here – fetched separately via dashboard

	// ── Eligibility (root-level, matching types.ts) ───────────────────────────
	private BigDecimal minUgScore;
	private String formatUg; // was missing

	private BigDecimal min10thScore;
	private String format10th; // was missing

	private BigDecimal min12thScore;
	private String format12th; // was missing

	private BigDecimal minDiplomaScore; // was missing
	private String formatDiploma; // was missing

	private Integer maxBacklogs;
	private Boolean isDiplomaEligible;
	private Boolean allowGaps;
	private Integer maxGapYears;

	// ── JSON-parsed arrays (names match types.ts array fields exactly) ────────
	// types.ts declares these as string[] – backend parses JSON strings into
	// List<String>
	private List<String> responsibilitiesJson; // was "responsibilities"
	private List<String> qualificationsJson; // was "qualifications"
	private List<String> preferredQualificationsJson; // was "preferredQualifications"
	private List<String> benefitsJson; // was "benefits"

	// ── Branch / batch / rounds / required fields ──────────────────────────────
	private List<String> allowedBranches; // parsed List (not JSON string)
	private List<String> eligibleBatches; // parsed List (not JSON string)
	private List<Map<String, Object>> rounds; // parsed list
	private List<String> requiredStudentFields; // was "requiredFields"
	private List<Map<String, String>> documents; // was "attachments" – matches types.ts

}