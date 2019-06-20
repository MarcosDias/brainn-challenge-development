package com.mdias.javaspringpostgresproject.domain;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(exclude = "projectsStarred")
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Entity
@Table(name = "`user`")
@SequenceGenerator(name = "USER_SEQ", sequenceName = "USER_SEQ", initialValue = 1, allocationSize = 1)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ")
	private Long id;

	@Column
	@CreationTimestamp
	private LocalDateTime createdDateTime;

	@Column(nullable = false, length = 150)
	private String name;

	@ToString.Exclude
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private Set<ProjectStarred> projectsStarred;
}