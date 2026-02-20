package com.srots.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "college_company_subscriptions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CollegeCompanySubscription {
	@EmbeddedId
	private SubscriptionId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("collegeId")
	@JoinColumn(name = "college_id")
	private College college;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("companyId")
	@JoinColumn(name = "company_id")
	private GlobalCompany company;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "added_by_id")
	private User addedBy;

}