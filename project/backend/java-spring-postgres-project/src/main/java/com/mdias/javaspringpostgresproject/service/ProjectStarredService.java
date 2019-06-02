package com.mdias.javaspringpostgresproject.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mdias.javaspringpostgresproject.domain.ProjectStarred;
import com.mdias.javaspringpostgresproject.domain.User;
import com.mdias.javaspringpostgresproject.dto.ProjectGitHubDTO;
import com.mdias.javaspringpostgresproject.exception.UserNotFoundGitHubException;
import com.mdias.javaspringpostgresproject.repositry.ProjectStarredRepository;
import com.mdias.javaspringpostgresproject.transformer.impl.TransformerGitHubProjectToProject;

@Service
public class ProjectStarredService {

	private static final TransformerGitHubProjectToProject transformer = new TransformerGitHubProjectToProject();

	@Autowired
	private LoadUserGithubService loadUserGithubService;

	@Autowired
	private ProjectStarredRepository projectStarredRepository;

	@Autowired
	private UserService userService;

	public User loadUser(final String username) throws UserNotFoundGitHubException {

		List<ProjectGitHubDTO> projectStarredGithub = loadUserGithubService.loadProjectStarred(username);

		Set<ProjectStarred> listProjectsStar = transformer.transform(projectStarredGithub);

		User user = getUserLoadedorCreateUser(username);

		projectStarredRepository.deleteByUser(user);

		user.setProjectsStarred(listProjectsStar);

		listProjectsStar.forEach(project -> project.setUser(user));
		projectStarredRepository.saveAll(listProjectsStar);

		return user;
	}

	private User getUserLoadedorCreateUser(String username) {
		Optional<User> optUser = userService.findByName(username);
		User user = null;

		if (optUser.isPresent()) {
			user = optUser.get();
		} else {
			user = User.builder().name(username).build();
			user = userService.save(user);
		}

		return user;
	}

}
