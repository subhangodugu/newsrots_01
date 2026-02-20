package com.srots.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;

@Entity
@Table(name = "global_companies", indexes = {
		@Index(name = "idx_company_name", columnList = "name"),
		@Index(name = "idx_company_headquarters", columnList = "headquarters")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GlobalCompany {

	@Id
	@Column(name = "id", length = 36, columnDefinition = "VARCHAR(36)", updatable = false, nullable = false)
	private String id;

	@Column(nullable = false, unique = true)
	private String name;

	private String website;
	private String logoUrl;

	@Column(columnDefinition = "TEXT")
	private String description;

	@JdbcTypeCode(SqlTypes.JSON)
	@Column(name = "address_json", columnDefinition = "json")
	private String addressJson;

	private String headquarters;
	private String fullAddress;

	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CollegeCompanySubscription> subscriptions;

}