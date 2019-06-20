package com.mdias.javaspringpostgresproject.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(exclude = "projectStarred")
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Entity
@Table(name = "tag")
@SequenceGenerator(name = "TAG_SEQ", sequenceName = "TAG_SEQ", initialValue = 1, allocationSize = 1)
public class Tag {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TAG_SEQ")
	private Long id;

	@Column
	@CreationTimestamp
	private LocalDateTime createdDateTime;

	@Column(nullable = false, length = 100)
	private String name;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = ProjectStarred.class)
	@JoinColumn(name = "projectstar_id", nullable = false)
	private ProjectStarred projectStarred;
}
