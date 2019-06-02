package com.mdias.javaspringpostgresproject.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name = "projectstarred")
public class ProjectStarred {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	protected Long id;

	@Column
	@CreationTimestamp
	protected LocalDateTime createdDateTime;

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

	@ManyToOne
	@JoinColumn(name = "user_id",  nullable = false)
	private User user;

}
