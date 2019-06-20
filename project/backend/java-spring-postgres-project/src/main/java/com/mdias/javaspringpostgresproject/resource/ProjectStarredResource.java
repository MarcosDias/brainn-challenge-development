package com.mdias.javaspringpostgresproject.resource;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProjectStarredResource {
	
	private Long id;
	private LocalDateTime createdDateTime;
	private Long githubId;
	private String name;
	private String description;
	private String url;
	private String language;
	private List<TagResource> tags;

}
