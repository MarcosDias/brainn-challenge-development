package com.mdias.javaspringpostgresproject.transformer.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.mdias.javaspringpostgresproject.domain.Tag;
import com.mdias.javaspringpostgresproject.resource.TagResource;
import com.mdias.javaspringpostgresproject.transformer.Transformer;

public class TransformerListTagToListTagResource implements Transformer<List<Tag>, List<TagResource>> {

	@Override
	public List<TagResource> transform(List<Tag> listInput) {
		return listInput.stream()
				.map(in -> TagResource.builder()
						.id(in.getId())
						.name(in.getName())
						.build())
				.collect(Collectors.toList());
	}

}
