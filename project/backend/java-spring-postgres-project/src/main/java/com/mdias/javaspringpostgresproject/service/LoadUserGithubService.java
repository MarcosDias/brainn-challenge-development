package com.mdias.javaspringpostgresproject.service;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.mdias.javaspringpostgresproject.dto.ProjectGitHubDTO;
import com.mdias.javaspringpostgresproject.exception.UserNotFoundGitHubException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LoadUserGithubService {

	@Value("${integration.github.api}")
	private String apiUrl;

	@Autowired
	private RestTemplate restTemplate;

	public List<ProjectGitHubDTO> loadProjectStarred(final String username) throws UserNotFoundGitHubException {
		ProjectGitHubDTO[] response = null;

		try {
			log.info("[ loadProjectStars ] - Searching for user {} starred repositories", username);

			response = restTemplate.getForObject(buildUrl(username), ProjectGitHubDTO[].class);

			log.info("[ loadProjectStars ] - Were found {} starred repositories of user {}", response.length, username);

		} catch (Exception e) {
			log.error("Erro ao buscar repostórios estrelados do usuário {}. {}", username, e.getMessage());

			throw new UserNotFoundGitHubException(String.format("User \"%s\" not found on GitHub!", username));
		}

		return List.of(response);
	}

	private URI buildUrl(final String username) {
		return UriComponentsBuilder
				.fromUriString(apiUrl)
				.buildAndExpand(username)
				.toUri();
	}

}
