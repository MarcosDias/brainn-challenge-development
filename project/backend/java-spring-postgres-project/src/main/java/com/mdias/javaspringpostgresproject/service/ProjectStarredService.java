package com.mdias.javaspringpostgresproject.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.mdias.javaspringpostgresproject.domain.ProjectStarred;
import com.mdias.javaspringpostgresproject.domain.Tag;
import com.mdias.javaspringpostgresproject.domain.User;
import com.mdias.javaspringpostgresproject.dto.ProjectGitHubDTO;
import com.mdias.javaspringpostgresproject.exception.ResourceNotFound;
import com.mdias.javaspringpostgresproject.exception.UserNotFoundGitHubException;
import com.mdias.javaspringpostgresproject.repository.ProjectStarredRepository;
import com.mdias.javaspringpostgresproject.repository.TagRepository;
import com.mdias.javaspringpostgresproject.resource.TagResource;
import com.mdias.javaspringpostgresproject.transformer.impl.TransformerGitHubProjectToProject;
import com.mdias.javaspringpostgresproject.transformer.impl.TransformerListTagResourceToListTag;

@Service
public class ProjectStarredService {

	private static final TransformerGitHubProjectToProject transformerGithubProjectToProject = new TransformerGitHubProjectToProject();
	private static final TransformerListTagResourceToListTag transformerResourceToTag = new TransformerListTagResourceToListTag();

	@Autowired
	private LoadUserGithubService loadUserGithubService;

	@Autowired
	private ProjectStarredRepository projectStarredRepository;

	@Autowired
	private TagRepository tagRepository;

	@Autowired
	private UserService userService;

	@Transactional
	public User loadUser(final String username) throws UserNotFoundGitHubException {

		List<ProjectGitHubDTO> projectStarredGithub = loadUserGithubService.loadProjectStarred(username);

		Set<ProjectStarred> listProjectsStar = transformerGithubProjectToProject.transform(projectStarredGithub);

		User user = userService.getUserLoadedOrCreate(username);

		projectStarredRepository.deleteByUser(user);

		user.setProjectsStarred(listProjectsStar);

		listProjectsStar.forEach(project -> project.setUser(user));
		projectStarredRepository.saveAll(listProjectsStar);

		return user;
	}

	@Transactional
	public Page<ProjectStarred> findProjectStar(String username, int page, int size) throws ResourceNotFound {

		User user = userService.getUserLoaded(username);

		return projectStarredRepository.findByUser(user, PageRequest.of(page, size, Sort.by(Direction.ASC, "name")));
	}

	@Transactional
	public ProjectStarred setTagInProjectStarred(String username, Long projectId, List<TagResource> tags) throws ResourceNotFound {

		User user = userService.getUserLoaded(username);
		ProjectStarred project = getProjectStarred(projectId, user);

		tagRepository.deleteByProjectStarred(project);
		
		if (!tags.isEmpty()) {
			List<Tag> listTag = transformerResourceToTag.transform(tags);
			listTag.forEach(t -> t.setProjectStarred(project));
			tagRepository.saveAll(listTag);			
		}

		return projectStarredRepository.getOne(projectId);
	}

	private ProjectStarred getProjectStarred(Long projectId, User user) throws ResourceNotFound {
		Optional<ProjectStarred> optProject = projectStarredRepository.findByIdAndUser(projectId, user);
		return optProject.orElseThrow(() -> new ResourceNotFound("Project not found, sorry! :("));
	}

}
