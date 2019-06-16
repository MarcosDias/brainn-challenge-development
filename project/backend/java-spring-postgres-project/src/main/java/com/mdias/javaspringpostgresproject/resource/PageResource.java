package com.mdias.javaspringpostgresproject.resource;

import java.util.List;

import lombok.Getter;

@Getter
public class PageResource <K> {

	private List<K> content;
	private int pageNumber;
	private int pageSize;
	private int totalElements;
	private int totalPages;
	
	public PageResource(List<K> content, int pageNumber, int pageSize, int totalElements, int totalPages) {
		this.content = content;
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.totalElements = totalElements;
		this.totalPages = totalPages;
	}
}
