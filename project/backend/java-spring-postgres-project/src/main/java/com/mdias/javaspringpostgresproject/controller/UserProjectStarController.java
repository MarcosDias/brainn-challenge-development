package com.mdias.javaspringpostgresproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mdias.javaspringpostgresproject.domain.ProjectStarred;
import com.mdias.javaspringpostgresproject.exception.ErrorData;
import com.mdias.javaspringpostgresproject.exception.ResourceNotFound;
import com.mdias.javaspringpostgresproject.resource.PageResource;
import com.mdias.javaspringpostgresproject.resource.ProjectStarredResource;
import com.mdias.javaspringpostgresproject.resource.TagResource;
import com.mdias.javaspringpostgresproject.service.ProjectStarredService;
import com.mdias.javaspringpostgresproject.transformer.impl.TransformerProjectStarredToProjectStarredResource;
import com.mdias.javaspringpostgresproject.transformer.impl.TransformerSetProjectStarredToSetProjectStarredResource;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/user")
public class UserProjectStarController {
	
	private TransformerSetProjectStarredToSetProjectStarredResource transformSetProject = new TransformerSetProjectStarredToSetProjectStarredResource();
	private TransformerProjectStarredToProjectStarredResource transformProject = new TransformerProjectStarredToProjectStarredResource();

	@Autowired
	private ProjectStarredService service;

	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Return list of project starred of user."),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorData.class) 
	})
	@GetMapping(value = "/{username}/projectstarred", params = { "page", "size" })
	public ResponseEntity<PageResource<ProjectStarredResource>> getListProjectStarred(
			@PathVariable final String username, 
			@RequestParam(name = "page", defaultValue = "1") final int page,
			@RequestParam(name = "size", defaultValue = "10") final int size) throws ResourceNotFound {

		Page<ProjectStarred> pageProjectStar = service.findProjectStar(username, page, size);
	
		PageResource<ProjectStarredResource> pageResource = new PageResource<>(
				transformSetProject.transform(pageProjectStar.getContent()), 
			pageProjectStar.getPageable().getPageNumber(), 
			pageProjectStar.getPageable().getPageSize(), 
			(int) pageProjectStar.getTotalElements(), 
			pageProjectStar.getTotalPages()
		);

		return ResponseEntity.ok(pageResource);
	}

	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Project that had your tag list updated.", response = ProjectStarredResource.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorData.class) 
	})
	@PutMapping(value = "/{username}/projectstarred/{projectId}/tags", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProjectStarredResource> setTagInProjectStar(	
			@PathVariable final String username,
			@PathVariable final Long projectId,
			@RequestBody final List<TagResource> tags) throws ResourceNotFound {
		
		ProjectStarred project = service.setTagInProjectStarred(username, projectId, tags);
		
		ProjectStarredResource projectStarredResource = transformProject.transform(project);
		
		return ResponseEntity.ok(projectStarredResource);
	}

}
