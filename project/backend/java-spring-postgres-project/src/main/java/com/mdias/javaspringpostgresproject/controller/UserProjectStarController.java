package com.mdias.javaspringpostgresproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mdias.javaspringpostgresproject.domain.ProjectStarred;
import com.mdias.javaspringpostgresproject.exception.ResourceNotFound;
import com.mdias.javaspringpostgresproject.resource.PageResource;
import com.mdias.javaspringpostgresproject.resource.ProjectStarredResource;
import com.mdias.javaspringpostgresproject.service.ProjectStarredService;
import com.mdias.javaspringpostgresproject.transformer.impl.TransformerSetProjectStarredToSetProjectStarredResource;

@RestController
@RequestMapping("/user")
public class UserProjectStarController {
	
	TransformerSetProjectStarredToSetProjectStarredResource transform = new TransformerSetProjectStarredToSetProjectStarredResource();

	@Autowired
	private ProjectStarredService service;

	@GetMapping(value = "/{username}/projectstarred", params = { "page", "size" })
	public ResponseEntity<PageResource<ProjectStarredResource>> getListProjectStarred(
			@PathVariable final String username, 
			@RequestParam(name = "page", defaultValue = "1") final int page,
			@RequestParam(name = "size", defaultValue = "10") final int size) throws ResourceNotFound {

		Page<ProjectStarred> pageProjectStar = service.findProjectStar(username, page, size);
	
		PageResource<ProjectStarredResource> pageResource = new PageResource<>(
			transform.transform(pageProjectStar.getContent()), 
			pageProjectStar.getPageable().getPageNumber(), 
			pageProjectStar.getPageable().getPageSize(), 
			(int) pageProjectStar.getTotalElements(), 
			pageProjectStar.getTotalPages()
		);

		return ResponseEntity.ok(pageResource);
	}

//	@PutMapping(value = "/{username}/projectstar/{projectId}/tags", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	public ResponseEntity<ProjectStarred> setTagInProjectStar(	
//			@PathVariable final String username,
//			@PathVariable final Long projectId,
//			@RequestBody final List<Tag> tags) throws NotFoundException {
//		
//		ProjectStarred project = service.setTagInProjectStar(username, projectId, tags);
//		
//		return new ResponseEntity<>(project, HttpStatus.OK);
//	}

}
