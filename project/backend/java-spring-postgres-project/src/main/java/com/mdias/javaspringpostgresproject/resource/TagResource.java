package com.mdias.javaspringpostgresproject.resource;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TagResource {
	private Long id;
	private String name;
}
