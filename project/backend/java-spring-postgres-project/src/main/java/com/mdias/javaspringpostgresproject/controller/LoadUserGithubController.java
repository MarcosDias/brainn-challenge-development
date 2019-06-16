package com.mdias.javaspringpostgresproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mdias.javaspringpostgresproject.domain.User;
import com.mdias.javaspringpostgresproject.exception.UserNotFoundGitHubException;
import com.mdias.javaspringpostgresproject.resource.UserResource;
import com.mdias.javaspringpostgresproject.service.ProjectStarredService;
import com.mdias.javaspringpostgresproject.transformer.impl.TransformerUserToUserResource;

@RestController
@RequestMapping("/loaduser")
public class LoadUserGithubController {
	
	private TransformerUserToUserResource transformer = new TransformerUserToUserResource();
	
	@Autowired
	private ProjectStarredService service;

	@PostMapping(value = "/{username}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<UserResource> loadUserGithub(@PathVariable final String username) throws UserNotFoundGitHubException {
		User loadedUser = service.loadUser(username);
		return ResponseEntity.ok(transformer.transform(loadedUser));
	}

}
