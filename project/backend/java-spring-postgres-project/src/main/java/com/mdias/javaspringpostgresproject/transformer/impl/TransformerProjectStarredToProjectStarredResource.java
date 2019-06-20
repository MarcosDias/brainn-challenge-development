package com.mdias.javaspringpostgresproject.transformer.impl;

import com.mdias.javaspringpostgresproject.domain.ProjectStarred;
import com.mdias.javaspringpostgresproject.resource.ProjectStarredResource;
import com.mdias.javaspringpostgresproject.transformer.Transformer;

public class TransformerProjectStarredToProjectStarredResource implements Transformer<ProjectStarred, ProjectStarredResource>{
	
	private final TransformerListTagToListTagResource transformer = new TransformerListTagToListTagResource();

	@Override
	public ProjectStarredResource transform(ProjectStarred in) {
		return ProjectStarredResource.builder()
				.id(in.getId())
				.githubId(in.getGithubId())
				.name(in.getName())
				.description(in.getDescription())
				.url(in.getUrl())
				.language(in.getLanguage())
				.tags(transformer.transform(in.getTags()))
				.build();
	}

}
