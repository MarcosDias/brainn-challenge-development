package com.mdias.javaspringpostgresproject.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@EqualsAndHashCode(exclude = { "user", "tags" })
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Entity
@Table(name = "projectstarred")
@SequenceGenerator(name = "PROJECT_STARRED_SEQ", sequenceName = "PROJECT_STARRED_SEQ", initialValue = 1, allocationSize = 1)
public class ProjectStarred {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROJECT_STARRED_SEQ")
	private Long id;

	@Column
	@CreationTimestamp
	private LocalDateTime createdDateTime;

	@Column(nullable = false)
	private Long githubId;

	@Column(nullable = false, length = 300)
	private String name;

	@Column(length = 1000)
	private String description;

	@Column(nullable = false, length = 1000)
	private String url;

	@Column(length = 100)
	private String language;

	@ToString.Exclude
	@ManyToOne
	@JoinColumn(name = "user_id",  nullable = false)
	private User user;
	
	@OneToMany(
			mappedBy = "projectStarred",
			targetEntity = Tag.class,
			fetch = FetchType.LAZY)
	private List<Tag> tags;

}
