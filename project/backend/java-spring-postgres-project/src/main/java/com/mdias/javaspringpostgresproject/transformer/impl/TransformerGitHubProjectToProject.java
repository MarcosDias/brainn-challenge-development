package com.mdias.javaspringpostgresproject.transformer.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.mdias.javaspringpostgresproject.domain.ProjectStarred;
import com.mdias.javaspringpostgresproject.dto.ProjectGitHubDTO;
import com.mdias.javaspringpostgresproject.transformer.Transformer;

public class TransformerGitHubProjectToProject implements Transformer<List<ProjectGitHubDTO>, Set<ProjectStarred>> {

	@Override
	public Set<ProjectStarred> transform(List<ProjectGitHubDTO> input) {
		return input.stream()
			.map(in -> {
				return ProjectStarred.builder()
					.githubId(in.getId())
					.language(in.getLanguage())
					.name(in.getName())
					.url(in.getUrl())
					.build();
			}).collect(Collectors.toSet());
	}

}
