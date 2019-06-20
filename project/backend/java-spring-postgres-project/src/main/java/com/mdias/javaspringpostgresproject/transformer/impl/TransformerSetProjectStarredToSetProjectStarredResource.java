package com.mdias.javaspringpostgresproject.transformer.impl;

import static java.util.stream.Collectors.toList;

import java.util.List;

import com.mdias.javaspringpostgresproject.domain.ProjectStarred;
import com.mdias.javaspringpostgresproject.resource.ProjectStarredResource;
import com.mdias.javaspringpostgresproject.transformer.Transformer;

public class TransformerSetProjectStarredToSetProjectStarredResource implements Transformer<List<ProjectStarred>, List<ProjectStarredResource>> {
	
	private final TransformerProjectStarredToProjectStarredResource transformer = new TransformerProjectStarredToProjectStarredResource(); 

	@Override
	public List<ProjectStarredResource> transform(List<ProjectStarred> setInput) {
		return setInput.stream()
			.map(transformer::transform)
			.collect(toList());
	}

}
