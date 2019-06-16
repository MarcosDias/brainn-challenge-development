package com.mdias.javaspringpostgresproject.transformer.impl;

import static java.util.stream.Collectors.toList;

import java.util.List;

import com.mdias.javaspringpostgresproject.domain.ProjectStarred;
import com.mdias.javaspringpostgresproject.resource.ProjectStarredResource;
import com.mdias.javaspringpostgresproject.transformer.Transformer;

public class TransformerSetProjectStarredToSetProjectStarredResource implements Transformer<List<ProjectStarred>, List<ProjectStarredResource>> {

	@Override
	public List<ProjectStarredResource> transform(List<ProjectStarred> setInput) {
		return setInput.stream()
			.map(in -> ProjectStarredResource.builder()
					.id(in.getId())
					.createdDateTime(in.getCreatedDateTime())
					.githubId(in.getGithubId())
					.name(in.getName())
					.description(in.getDescription())
					.url(in.getUrl())
					.language(in.getLanguage())
					.build())
			.collect(toList());
	}

}
