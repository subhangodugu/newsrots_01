// 8. StudentLanguage.java
package com.srots.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "student_languages")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentLanguage {
	@Id
	@Column(length = 36)
	private String id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id")
	@JsonBackReference
	private User student;
	private String name;
	@Enumerated(EnumType.STRING)
	private LangProficiency proficiency = LangProficiency.Elementary;

	public enum LangProficiency {
		Fundamental, Elementary, Limited_Working, Professional_Working, Native
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof StudentLanguage))
			return false;
		return id != null && id.equals(((StudentLanguage) o).id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

}