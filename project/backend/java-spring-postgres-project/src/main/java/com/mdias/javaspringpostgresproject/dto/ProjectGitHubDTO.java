package com.mdias.javaspringpostgresproject.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectGitHubDTO {
	private Long id;
	private String name;
	private String description;
	private String url;
	private String language;
}
