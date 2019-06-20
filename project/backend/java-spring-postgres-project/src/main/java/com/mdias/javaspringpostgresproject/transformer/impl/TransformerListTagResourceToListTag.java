package com.mdias.javaspringpostgresproject.transformer.impl;

import static java.util.stream.Collectors.toList;

import java.util.List;

import com.mdias.javaspringpostgresproject.domain.Tag;
import com.mdias.javaspringpostgresproject.resource.TagResource;
import com.mdias.javaspringpostgresproject.transformer.Transformer;

public class TransformerListTagResourceToListTag implements Transformer<List<TagResource>, List<Tag>> {

	@Override
	public List<Tag> transform(List<TagResource> listInput) {
		return listInput.stream()
			.map(in -> Tag.builder()
					.name(in.getName())
					.build())
			.collect(toList());
	}

}
