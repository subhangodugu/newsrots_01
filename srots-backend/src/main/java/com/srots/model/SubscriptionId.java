package com.srots.model;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor // Required by Hibernate
@AllArgsConstructor
@EqualsAndHashCode // Explicitly added to satisfy Hibernate warnings
public class SubscriptionId implements Serializable {

	@Column(name = "college_id", length = 36, columnDefinition = "VARCHAR(36)")
	private String collegeId;

	@Column(name = "company_id", length = 36, columnDefinition = "VARCHAR(36)")
	private String companyId;

}